package ca.polymtl.ourscureuil;

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
				Truck1 newTruck1 = new Truck1(waypointsProjectiles[idPos-1]);
				mStage.addActor(newTruck1);
				break;
			case TRUCK2:
				Truck2 newTruck2 = new Truck2(waypointsProjectiles[idPos-1]);
				mStage.addActor(newTruck2);
				break;
			case FASTCAR1:
				FastCar1 newFastCar1 = new FastCar1(waypointsProjectiles[idPos-1]);
				mStage.addActor(newFastCar1);
				break;
			case FASTCAR2:
				FastCar2 newFastCar2 = new FastCar2(waypointsProjectiles[idPos-1]);
				mStage.addActor(newFastCar2);
				break;
			case FASTCAR3:
				FastCar3 newFastCar3 = new FastCar3(waypointsProjectiles[idPos-1]);
				mStage.addActor(newFastCar3);
				break;
			case MOTORCYCLE:
				Motorcycle1 newMotorcycle1 = new Motorcycle1(waypointsProjectiles[idPos-1]);
				mStage.addActor(newMotorcycle1);
				break;
			case VAN1:
				Van1 newVan1 = new Van1(waypointsProjectiles[idPos-1]);
				mStage.addActor(newVan1);
				break;
			case VAN2:
				Van2 newVan2 = new Van2(waypointsProjectiles[idPos-1]);
				mStage.addActor(newVan2);
				break;
			case COP:
				Cop1 newCop1 = new Cop1(waypointsProjectiles[idPos-1]);
				mStage.addActor(newCop1);
				break;
			case BARREL:
				Barrel1 newBarrel1 = new Barrel1(waypointsProjectiles[idPos-1]);
				mStage.addActor(newBarrel1);
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