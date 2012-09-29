package ca.polymtl.ourscureuil;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import ca.polymtl.ourscureuil.Projectile.ProjectileType;

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
	ArrayList<Node> children = new ArrayList<Node>();
	static Stage mStage;

	private final int maximumNumberOfMonster = 30;
	private int numberOfMonster = 0;
	private Vector2[] waypointsProjectiles;

	public RenderTree() {
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		numberOfMonster = 0;
		setProjectilesWaypoints(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	}

	
	private void setProjectilesWaypoints(int w, int h)
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
	}
	
	public void addProjectile(int idPos, ProjectileType projectileType) {
		Projectile nouveauProjectile = new Projectile(waypointsProjectiles[idPos-1],projectileType,mStage);
		children.add(nouveauProjectile);

	}

	public void draw() {

		// this.instantiateMonsters();
		
		mStage.draw();
		this.drawChildren();


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

	public Stage getStage() {
		return mStage;
	}

	private void drawChildren() {
		Iterator<Node> iter = children.iterator();
		Node child = null;

		while (iter.hasNext()) {
			child = iter.next();
			child.update(mStage);
		}
	}

	/*
	 * private void instantiateMonsters() { if(numberOfMonster <
	 * maximumNumberOfMonster) { if(Math.random() < 0.1f) { Monster monster =
	 * new Monster(RenderTree.mStage); this.children.add(monster);
	 * RenderTree.mStage.addActor(monster.getImage()); this.numberOfMonster++; }
	 * } }
	 */
}