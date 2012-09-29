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
	
	
	
	public void reactCollisionFrogs(ArrayList<Frog> frogs, ArrayList<Projectile> projectiles)
	{
		for(int i=0; i< frogs.size(); i++)
		{
			for(int j=0; j< projectiles.size();j++)
			{
				Node frog = frogs.get(i);
				Node projectile = projectiles.get(j);
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
				
				
				
//				System.out.println("projectile.x:" + Double.toString(projectile.x)
//						+ ", projectile.y:" + Double.toString(projectile.y)
//						+ ", projectile.width:" + Double.toString(projectile.width)
//						+ ", projectile.height:" + Double.toString(projectile.height)
//						+ ", projectile center:" + Double.toString(projectile.x+projectile.width/2) + ","
//						+ Double.toString(projectile.y+projectile.height/2)
//						);
//				System.out.println("grenou.x:" + Double.toString(frog.x)
//						+ ", grenou.y:" + Double.toString(frog.y)
//						+ ", grenou.width:" + Double.toString(frog.width)
//						+ ", grenou.height:" + Double.toString(frog.height)
//						+ ", grenou center:" + Double.toString(frog.x+frog.width/2) + ","
//						+ Double.toString(frog.y+frog.height/2)
//						);
				
				
				if(intersectionRoundRound(c1, r1, c2, r2))
					this.getCurrentStage().removeActor(frog);
				
				
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
	

}