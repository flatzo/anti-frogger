package ca.polymtl.ourscureuil;
 
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Repeat;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

public class BarrelExplosion extends Node {
	
	static final float HITBOX_WIDTH = 96.0f;
	static final float HITBOX_HEIGHT = 96.0f;
	
	public BarrelExplosion(Vector2 posStart /*, String nameOfNode*/) { //position start is left, bottom corner
		super(posStart, "BarrelExplosion");
		this.height = HITBOX_HEIGHT;
		this.width  = HITBOX_WIDTH;
		// TODO Auto-generated constructor stub
	}
	
	protected static final Texture projectileTextureList = new Texture(Gdx.files.internal("data/vehicles_512.png"));
	
	//x = 48+48 = 96
	// y = 144
	// w = 48
	// h = 48
	
	//static protected TextureRegion texregDead = new TextureRegion(projectileTextureList, 96, 144, 48, 48); //grenouille morte 
	//static protected TextureRegion texregBarrelExplosion = new TextureRegion(projectileTextureList, 144, 192, 96, 96); //car crash smoke
	static protected TextureRegion texregBarrelExplosion = new TextureRegion(projectileTextureList, 0, 192, 96, 96); //barrel explosion
	
	
	public void SetMovement(Vector2 movement) {
		//dead frog does not move
		
		//this.action(Repeat.$(Sequence.$(MoveBy.$(movement.x, movement.y, 1), MoveBy.$(-movement.x, movement.y, 1)),5));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		//System.out.println("(" + Double.toString(x) + "," + Double.toString(y) + ")");
		
		
		batch.draw(texregBarrelExplosion,this.originX+this.x,this.originY+this.y,HITBOX_WIDTH/2,HITBOX_HEIGHT/2,HITBOX_WIDTH,HITBOX_HEIGHT,1.0f,1.0f,this.rotation);
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
