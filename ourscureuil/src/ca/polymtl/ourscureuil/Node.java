package ca.polymtl.ourscureuil;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;



public abstract class Node extends Actor {
	
	public Node(Vector2 posStart, String nameOfNode) { //position start is left, bottom corner
		super(nameOfNode);
		setPosition(posStart.x,posStart.y);
	}

	public abstract void draw (SpriteBatch batch, float parentAlpha);
	

	public String getName() {
		return this.name;
	}
	
	public void setPosition( float newX, float newY ) {
		this.x = newX;
		this.y = newY;
	}


}