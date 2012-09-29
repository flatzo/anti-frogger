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
	static protected TextureRegion texreg = new TextureRegion(projectileTextureList, 0, 48, 48, 48);	
	
	public Motorcycle1( Vector2 posStart ) {
		super ( posStart );
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(texreg,this.originX+this.x,this.originY+this.y);
	}

	@Override
	public Actor hit(float x, float y) {
		float h_tot = Gdx.graphics.getHeight();
		if(	x>=(this.originX+this.x) && x<=(this.originX+this.x+HITBOX_WIDTH) && (h_tot-y)>=(this.originY+this.y) && (h_tot-y)<=(this.originY+this.y+HITBOX_HEIGHT)) {
			return this;
		}
		return null;
	}

	@Override
	public float GetProjectileMaxSpeed() {
		return MAX_SPEED;
	}
}
