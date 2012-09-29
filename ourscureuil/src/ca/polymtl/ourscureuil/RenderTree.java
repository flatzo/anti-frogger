package ca.polymtl.ourscureuil;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import ca.polymtl.ourscureuil.Projectile.ProjectileType;
import ca.polymtl.ourscureuil.projectiles.Truck1;

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
	private Vector2[] waypointsProjectiles;

	public RenderTree(float w, float h, boolean stretch, SpriteBatch batch) {
		mStage = new Stage(w,h,stretch,batch);
		setProjectilesWaypoints(w,h);
	}
	
	private void setProjectilesWaypoints(float w, float h)
	{
		waypointsProjectiles = new Vector2[8];
		waypointsProjectiles[0] = new Vector2(w - 40,h - 96);
		waypointsProjectiles[1] = new Vector2(w - 40,h - 144);
		waypointsProjectiles[2] = new Vector2(w - 40,h - 192);
		waypointsProjectiles[3] = new Vector2(w - 40,h - 240);
		
		waypointsProjectiles[4] = new Vector2(0, 48);
		waypointsProjectiles[5] = new Vector2(0, 96);
        waypointsProjectiles[6] = new Vector2(0, 144);
        waypointsProjectiles[7] = new Vector2(0, 192);
        
        // add waypoints for counter-traffic?
	}
	
	public void addProjectile(int idPos, ProjectileType projectileType) {
		switch(projectileType) {
			case TRUCK1:
				Truck1 newProj = new Truck1(waypointsProjectiles[idPos-1]);
				mStage.addActor(newProj);
				break;
			case TRUCK2:
				
				break;
			case FASTCAR1:
				
				break;
			case FASTCAR2:
				
				break;
			case FASTCAR3:
				
				break;
			case MOTORCYCLE:
				
				break;
			case VAN1:
				
				break;
			case VAN2:
				
				break;
			case COP:
				
				break;
			case BARREL:
				
				break;
			default:
			break;	
		}

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
	
	public Vector2[] getWayPoints()
	{
		return waypointsProjectiles;
	}
	/*
	 * private void instantiateMonsters() { if(numberOfMonster <
	 * maximumNumberOfMonster) { if(Math.random() < 0.1f) { Monster monster =
	 * new Monster(RenderTree.mStage); this.children.add(monster);
	 * RenderTree.mStage.addActor(monster.getImage()); this.numberOfMonster++; }
	 * } }
	 */
}