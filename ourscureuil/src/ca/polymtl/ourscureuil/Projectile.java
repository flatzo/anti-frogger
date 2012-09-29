package ca.polymtl.ourscureuil;


import java.util.ArrayList; 

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;



public class Projectile extends Node {
	
	public enum ProjectileType { TRUCK, TAXI, MOTORCYCLE, COP, SPEEDCAR, VAN};
	
	static final float PROJECTILE_SPEED = 0.5f;
	static int projectileId = 0;

	static final Texture projectileList = new Texture(Gdx.files.internal("data/greenCar.png"));
	//Separer la sheet en plusieurs projectilList
	
	
	private float mTimeLeft = 3; 
	private Actor mCurrentActor;
	private Vector2 mCurrentDirection;
	

	
	
	Projectile (Vector2 posStart, ProjectileType projectileType, Stage stage) {
		super ( new Vector3(posStart.x,posStart.y, 2)
				, new String("projectile").concat( new Integer(projectileId).toString())
				, projectileList
				,  new Vector2(0,0)
				, stage 
				);
		
		mCurrentActor = stage.findActor(new String("projectile").concat( new Integer(projectileId).toString()));
		projectileId++;
		
	}
	
	
	@Override
	public boolean update (Stage stage) {
		/*
		float dTime = Gdx.graphics.getDeltaTime();
		mTimeLeft-= dTime;
		if (mTimeLeft < 0) {
			return true;
		}
		mCurrentActor.x += mCurrentDirection.x*100*dTime;
		mCurrentActor.y += mCurrentDirection.y*100*dTime;
		*/
		return false;
	}

}
