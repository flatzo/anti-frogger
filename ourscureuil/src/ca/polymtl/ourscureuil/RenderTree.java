package ca.polymtl.ourscureuil;


import java.awt.geom.RoundRectangle2D.Float;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import ca.polymtl.ourscureuil.Projectile.ProjectileType;
import ca.polymtl.ourscureuil.projectiles.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RenderTree {
	private ArrayList<Node> children = new ArrayList<Node>();
	private Stage mStage;

	private final int maximumNumberOfMonster = 30;
	private int numberOfMonster = 0;
	

	public RenderTree(float stagewidth, float stageheight, boolean stretch, SpriteBatch batch) {
		mStage = new Stage(stagewidth,stageheight,stretch,batch);
	}
	
	public void dispose() {
		for(Node child : children) {
			((Projectile) child ).dispose();
		}
		mStage.dispose();
	}

	public void draw() {
		// this.instantiateMonsters();
		mStage.draw();
	}
	
	public Projectile getProjectile(Actor actor) {
		Iterator<Node> itr = children.iterator();
		   
	    //use hasNext() and next() methods of Iterator to iterate through the elements
	    for ( int i = 0; i < children.size(); i++) {
	    	System.out.println(actor.name + " " + children.get(i).getName());
	    	if (actor.name == children.get(i).getName()) {
	    		return (Projectile)children.get(i);
	    	}
	    }
		
	    return null;
	}

	public Stage getCurrentStage() {
		return mStage;
	}
	
	
	
	public void reactCollisionFrogs(ArrayList<Frog> frogs, ArrayList<Projectile> projectiles, ArrayList<DeadFrog> deadFrogs)
	{
		int currentFrog=0;
		while (currentFrog< frogs.size() )
		{
			int currentProjectile=0;
			boolean collisionDetected = false;
			while (currentProjectile< projectiles.size() && !collisionDetected)
			{
				Node frog = frogs.get(currentFrog);
				Node projectile = projectiles.get(currentProjectile);
				float r1 = 0;
				float r2 = 0;
				
				if(frog.width < frog.height)
					r1 = frog.height/2;
				else
					r1 = frog.width/2;
				
				if(projectile.width < projectile.height)
					r2 = projectile.height/2;
				else
					r2 = projectile.width/2;
				
				Vector2 c1 = new Vector2(frog.x+ frog.width/2, frog.y + frog.height/2);
				Vector2 c2 = new Vector2(projectile.x+ projectile.width/2, projectile.y + projectile.height/2);
				
				if(intersectionRoundRound(c1, r1, c2, r2))
				{
					killFrog(frog, deadFrogs, frogs, currentFrog ); //i is the index of frogToKill in the frogs array
					//i = 0; //so that if i was last
					collisionDetected = true; // so that i is not used anymore, and reevaluated
					currentFrog = 0; //frog was changed
				}
				currentProjectile++;
				
			}
			
			if (!collisionDetected) {
				currentFrog++;
			}
			else {
				//size of frogs has diminished
			}
		}
	}
	
	public boolean intersectionRoundRound(Vector2 c1, float r1 , Vector2 c2,  float r2)
	{

	    final float a = r1 + r2;
	    final float dx = c1.x - c2.x;
	    final float dy = c1.y - c2.y;
	    
	    //Return true if collision
	    return a * a > (dx * dx + dy * dy);

	}
	
	public boolean intersectionRectangleRectangle(Vector2 startPos1, float height1, float width1,
													Vector2 startPos2, float height2, float width2) {
		final float padding = 10;

	     if ( startPos1.y        > startPos2.y 			+ padding &&
	    	  startPos1.y        < startPos2.y+height2 	- padding &&// 1y basegauche est entre y2 hauteur 
	          startPos1.x        < startPos2.x+width2 	- padding && 
	          startPos1.x        > startPos2.x 			+ padding  // 1x basegauche est entre 2x largeur
	        ) {
	         return true;
	     }
	     if ( startPos1.y+height1 > startPos2.y 		+ padding &&
	          startPos1.y+height1 < startPos2.y+height2	- padding && // 1y hautgauche est entre 2y hauteur
	          startPos1.x         < startPos2.x+width2	- padding &&
	          startPos1.x         > startPos2.x  		+ padding// 1x hautgauche est entre 2x largeur
	        ) {
	         return true;
	     }
	     if ( startPos1.y+height1 > startPos2.y 		+ padding &&
	          startPos1.y+height1 < startPos2.y+height2	- padding && //1y hautdroite est entre 2y hauteur
	          startPos1.x+width1  < startPos2.x+width2	- padding &&
	          startPos1.x+width1  > startPos2.x 		+ padding// 1x hautdroite est entre 2x largeur
	        ) {
	         return true;
	     }
	     if ( startPos1.y         < startPos2.y			+ padding &&
	          startPos1.y         > startPos2.y+height2 - padding && // 1y basdroite est entre 2y hauteur
	          startPos1.x+width1  < startPos2.x+width2	+ padding &&
	          startPos1.x+width1  > startPos2.x 		- padding//1x basdroite est entre 2x largeur 
	        ){ 
	         return true;
	     }
	     // may be necessary : if rect2 is entirely included in rect 1
	     //if ( ) 
	    //{
	    		 
	    //}
	    	 
	     return false;
	 
	}
	
	private final float DURATION_OF_CADAVER_IN_SECONDS = 1f;
	private final float DURATION_OF_SMOKE_CLOUD_IN_SECONDS = 1f;
	
	public void killAllFrog(ArrayList<DeadFrog> deadFrogs,
			ArrayList<Frog> liveFrogs) {
		while(!liveFrogs.isEmpty()) {
			int indexOfLiveFrog = liveFrogs.size()-1;
			Node frogToKill = liveFrogs.get(liveFrogs.size()-1);
			killFrog(frogToKill, deadFrogs, liveFrogs, indexOfLiveFrog);
		}
	}
	
	
	private void killFrog(Node frogToKill, ArrayList<DeadFrog> deadFrogs, ArrayList<Frog> liveFrogs, int indexOfLifeFrog) {
		final DeadFrog cadaver = new DeadFrog(new Vector2(frogToKill.x, frogToKill.y), "dead"+frogToKill.name);
		deadFrogs.add(cadaver);
		liveFrogs.remove(indexOfLifeFrog);
		this.getCurrentStage().addActor(cadaver);
		this.getCurrentStage().removeActor(frogToKill);
		
		//cadaver will disappear from the road
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		                getCurrentStage().removeActor(cadaver);
		            }
		        }, 
		        (long) (DURATION_OF_CADAVER_IN_SECONDS*1000) 
		);
		if (!MyGdxGame.isMuted) {
			MyGdxGame.squishSFX.play();
		}
		
	}

	private void crashCars(Node firstProjectile, Node secondProjectile, ArrayList<CarCrashSmoke> carCrashSmokeClouds
			, int firstProjectileIndex, int secondProjectileIndex, ArrayList<Projectile> projectiles) {
		
		final CarCrashSmoke smokeCloud = new CarCrashSmoke(new Vector2( (firstProjectile.x + secondProjectile.x)/2 , 
				         ( firstProjectile.y+ secondProjectile.y)/2), "crashed"+firstProjectile.name);
		carCrashSmokeClouds.add(smokeCloud);
		
		//if : precaution
		if (firstProjectileIndex < secondProjectileIndex) {
			projectiles.remove(secondProjectileIndex);
			projectiles.remove(firstProjectileIndex);
		}
		else {
			projectiles.remove(firstProjectileIndex);
			projectiles.remove(secondProjectileIndex);
		}
		
		this.getCurrentStage().addActor(smokeCloud);
		this.getCurrentStage().removeActor(firstProjectile);
		this.getCurrentStage().removeActor(secondProjectile);
		
		//cadaver will disappear from the road
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		                getCurrentStage().removeActor(smokeCloud);
		            }
		        }, 
		        (long) (DURATION_OF_SMOKE_CLOUD_IN_SECONDS*1000)
		);
		
		///THIERR!
		if (!MyGdxGame.isMuted) {
			MyGdxGame.carCrashSFX.play();
		}
		
		
		
	}
	

	public void reactCollisionProjectiles(ArrayList<Projectile> projectiles,
			ArrayList<CarCrashSmoke> carCrashSmokeClouds) {
		
		int firstProjectileIndex=0;
		while (firstProjectileIndex< projectiles.size() )
		{
			int secondProjectileIndex = firstProjectileIndex;
			boolean collisionDetected = false;
			while (secondProjectileIndex < projectiles.size() && !collisionDetected)
			{
				Node firstProjectile = projectiles.get(firstProjectileIndex);
				Node secondProjectile = projectiles.get(secondProjectileIndex);
				
				//Vector2 c1 = new Vector2(firstProjectile.x+ firstProjectile.width/2, firstProjectile.y + firstProjectile.height/2);
				//Vector2 c2 = new Vector2(secondProjectile.x+ secondProjectile.width/2, secondProjectile.y + secondProjectile.height/2);
				
				if(intersectionRectangleRectangle(new Vector2(firstProjectile.x, firstProjectile.y),
						firstProjectile.height,
						firstProjectile.width,
						new Vector2(secondProjectile.x, secondProjectile.y),
						secondProjectile.height,
						secondProjectile.width) )
				{
					System.out.println("CRASSSHSHHH");
					crashCars(firstProjectile, secondProjectile, carCrashSmokeClouds,
							firstProjectileIndex, secondProjectileIndex, projectiles);
					
					
					//i = 0; //so that if i was last
					collisionDetected = true; // so that i is not used anymore, and reevaluated
				
					firstProjectileIndex = 0; //the projectile group was changed
					secondProjectileIndex = 0;
				}
				else {
					secondProjectileIndex++;
				}
				
			}
			
			if (!collisionDetected) {
				firstProjectileIndex++;
			}
			else {
				//size of frogs has diminished
			}
			
			
		}
		
	}


}