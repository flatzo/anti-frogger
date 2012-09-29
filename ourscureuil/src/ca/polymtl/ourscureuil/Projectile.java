package ca.polymtl.ourscureuil;


import java.util.ArrayList;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;



public abstract class Projectile extends Node {
	
	static enum ProjectileType { TRUCK1, TRUCK2, FASTCAR1, FASTCAR2, FASTCAR3, MOTORCYCLE, VAN1, VAN2, COP, BARREL };
	static int projectileId = 0;
	protected static Texture projectileTextureList;
	
	private float mTimeLeft = 6;
	
	public abstract float GetProjectileMaxSpeed();
	
	public Projectile( Vector2 posStart ) {
		super ( posStart, new String("projectile").concat( new Integer(projectileId).toString()) );
		if(projectileTextureList == null) {
			projectileTextureList = new Texture(Gdx.files.internal("data/vehicles_512.png"));
			projectileTextureList.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		projectileId++;
	}
	
	public void SetMovement(Vector2 movement) {
		float speedfactor = GetProjectileMaxSpeed(); 
		
		float resistanceToMovement = 0.7f;
		
		this.action(MoveBy.$(movement.x*speedfactor*3,-movement.y*speedfactor*resistanceToMovement,mTimeLeft));
		float degsToRotate = (float) (MathUtils.radiansToDegrees*Math.tan((-movement.y*speedfactor*resistanceToMovement)/(movement.x*speedfactor*3)));
		this.action(RotateTo.$(degsToRotate,0.1f));
	}

}
