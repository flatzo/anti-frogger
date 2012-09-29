package ca.polymtl.ourscureuil.projectiles;

import java.util.ArrayList; 

import ca.polymtl.ourscureuil.Projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Motorcycle1 extends Projectile {
	
	static final float MAX_SPEED = 1.3f;
	static final float HITBOX_WIDTH = 48.0f;
	static final float HITBOX_HEIGHT = 48.0f;
	static protected TextureRegion texreg;	
	
	public Motorcycle1( Vector2 posStart ) {
		super ( posStart ); // sets X and Y
		this.width = HITBOX_WIDTH;
		this.height = HITBOX_HEIGHT;
		this.originX = HITBOX_WIDTH/2;
		this.originY = HITBOX_HEIGHT/2;
		this.scaleX = 1.0f;
		this.scaleY = 1.0f;
		this.rotation = 0.0f;
		if(texreg == null) {
			texreg = new TextureRegion(projectileTextureList, 0, 48, 48, 48);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texreg,this.x,this.y,this.originX,this.originY,this.width,this.height,this.scaleX,this.scaleY,this.rotation);
		//batch.draw(texreg,0,0);
	}

	@Override
	public Actor hit(float x, float y) {
		float h_tot = Gdx.graphics.getHeight();
		if(	x>=(this.x) && x<=(this.x+this.width) && (h_tot-y)>=(this.y) && (h_tot-y)<=(this.y+this.height)) {
			return this;
		}
		return null;
	}

	@Override
	public float GetProjectileMaxSpeed() {
		return MAX_SPEED;
	}

}

