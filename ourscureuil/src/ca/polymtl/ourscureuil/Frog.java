package ca.polymtl.ourscureuil;
 

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.Repeat;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;

public class Frog extends Node {
	
	static final float HITBOX_WIDTH = 48.0f;
	static final float HITBOX_HEIGHT = 48.0f;
	
	public Frog(Vector2 posStart, String nameOfNode) {
		super(posStart, nameOfNode);
		this.height = HITBOX_HEIGHT;
		this.width  = HITBOX_WIDTH;
		// TODO Auto-generated constructor stub
	}
	
	protected static final Texture projectileTextureList = new Texture(Gdx.files.internal("data/vehicles_512.png"));
	static protected TextureRegion texreg = new TextureRegion(projectileTextureList, 48, 144, 48, 48);	
	
	
	public void SetMovement(Vector2 movement) {
		this.action(Repeat.$(Sequence.$(MoveBy.$(movement.x, movement.y, 1), MoveBy.$(-movement.x, movement.y, 1)),7));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(this.y > 470) {
			this.remove();
			Score.getInstance().killALife();
		} else {
			batch.draw(texreg,this.originX+this.x,this.originY+this.y,HITBOX_WIDTH/2,HITBOX_HEIGHT/2,HITBOX_WIDTH,HITBOX_HEIGHT,1.0f,1.0f,this.rotation);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
