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



public abstract class Projectile extends Node {
	
	static enum ProjectileType { TRUCK1, TRUCK2, FASTCAR1, FASTCAR2, FASTCAR3, MOTORCYCLE, VAN1, VAN2, COP, BARREL };
	static int projectileId = 0;
	protected static final Texture projectileTextureList = new Texture(Gdx.files.internal("data/vehicles_512.png"));
	
	private float mTimeLeft = 3; 	
	
	public Projectile( Vector2 posStart ) {
		super ( posStart, new String("projectile").concat( new Integer(projectileId).toString()) );
		projectileId++;
	}

}
