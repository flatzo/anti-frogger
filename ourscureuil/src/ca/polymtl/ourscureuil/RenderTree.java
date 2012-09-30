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
	
	private final float DURATION_OF_CADAVER_IN_SECONDS = 100f;
	
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
	}


}