package ca.polymtl.ourscureuil;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
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

	public RenderTree() {
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		numberOfMonster = 0;
	}

	public void addProjectile(Vector2 posStart) {
		Projectile nouveauProjectile = new Projectile(posStart,mStage);
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