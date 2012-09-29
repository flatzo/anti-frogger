package ca.polymtl.ourscureuil;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;



public class Node {
	// sprite

	private String mName = new String();
	//private Texture texture;
	Vector3 relativePosition = new Vector3(0,0,0);
	private Vector2 mDistancePerRender = new Vector2(0,0);
	Image mImage;
	
	final int BACK = 0;
	final int FRONT = 1;
	
	public Node(Vector3 position, String nameOfNode,  Texture texture , Vector2 distancePerRender, Stage stage) {
		mImage = new Image(texture,Scaling.fill ,0, nameOfNode);
		mDistancePerRender = distancePerRender; 
		
		mName = nameOfNode;
		//relativePosition = position;
		mImage.x= position.x;
		mImage.y = position.y;
		//mImage. = nameOfNode;
		//Actor dd = new Actor(nameOfNode);
		
		//dd.
		
		//stage.addActor(dd);
		
		stage.addActor(mImage) ;

	}

	
	//bool true if you can delete it. 
	public boolean update(Stage stage) {
		return false;
	}

	public Vector2 getRelativePosition() {
		return new Vector2(relativePosition.x, relativePosition.y);
	}

	public String getName() {
		return mName;
	}
	
	Vector2 moveToward(Vector2 destination) {
		Vector2 distanceToTravel = new Vector2(destination.x - relativePosition.x, destination.y - relativePosition.y);

		
		Math.abs(3);
		if (Math.abs(distanceToTravel.x) < mDistancePerRender.x ) {
			relativePosition.x = destination.x;
		}
		else {
			relativePosition.x += distanceToTravel.x;
		}
		
		if (Math.abs(distanceToTravel.y) < mDistancePerRender.y ) {
			relativePosition.y = destination.y;
		}
		else {
			relativePosition.y += distanceToTravel.y;
		}
	
		
		return (new Vector2(distanceToTravel.x, distanceToTravel.y));
	}
	
	public Vector2 getDistancePerRender() {
		return mDistancePerRender;
	}
	public void setDistancePerRender(Vector2 newDistancePerRender) {
		mDistancePerRender = newDistancePerRender;
	}
	public Image getImage() {
		return mImage;
	}
	
	public void setPosition( float newX, float newY, Stage stage) {
		stage.findActor(mName).x = newX;
		stage.findActor(mName).y = newY;
	}


}