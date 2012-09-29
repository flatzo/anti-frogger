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
}