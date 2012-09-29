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

public class Frog extends Node {
	public Frog(Vector2 posStart, String nameOfNode) {
		super(posStart, nameOfNode);
		// TODO Auto-generated constructor stub
	}
	static final float HITBOX_WIDTH = 48.0f;
	static final float HITBOX_HEIGHT = 48.0f;
	protected static final Texture projectileTextureList = new Texture(Gdx.files.internal("data/vehicles_512.png"));
	static protected TextureRegion texreg = new TextureRegion(projectileTextureList, 144, 0, 48, 48);	
	
	private float mTimeLeft = 6;
	
	public void SetMovement(Vector2 movement) {
		float dX = this.x + movement.x;
//		float dX = (float) Math.sin(this.x + movement.x);
//		if(sinX > 0 && sinX < 0.5)
//			dX = 0.0f;
//		else if(dX < 0 && dX > -0.5) {
//			dX = 0.0f;
//		}
		this.action(Repeat.$(Sequence.$(MoveBy.$(movement.x, movement.y, 1), MoveBy.$(-movement.x, movement.y, 1)),5));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		batch.draw(texreg,this.originX+this.x,this.originY+this.y,HITBOX_WIDTH/2,HITBOX_HEIGHT/2,HITBOX_WIDTH,HITBOX_HEIGHT,1.0f,1.0f,this.rotation);
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}