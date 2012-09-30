package ca.polymtl.ourscureuil.projectiles;

import java.util.ArrayList; 

import ca.polymtl.ourscureuil.DeadFrog;
import ca.polymtl.ourscureuil.Frog;
import ca.polymtl.ourscureuil.MyGdxGame;
import ca.polymtl.ourscureuil.Node;
import ca.polymtl.ourscureuil.Projectile;
import ca.polymtl.ourscureuil.BarrelExplosion;
import ca.polymtl.ourscureuil.RenderTree;
import ca.polymtl.ourscureuil.Scene;

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

public class Barrel1 extends Projectile {
	
	static final float MAX_SPEED = 1.7f;
	public static final float HITBOX_WIDTH = 48.0f;
	static final float HITBOX_HEIGHT = 48.0f;
	static protected TextureRegion texreg;	
	
	public Barrel1( Vector2 posStart ) {
		super ( posStart, "Barrel1");
		this.width = HITBOX_WIDTH;
		this.height = HITBOX_HEIGHT;
		this.originX = HITBOX_WIDTH/2;
		this.originY = HITBOX_HEIGHT/2;
		this.scaleX = 1.0f;
		this.scaleY = 1.0f;
		this.rotation = 0.0f;
		if(posStart.x < this.originX)
			this.rotation = 180.0f;
		if(texreg == null) {
			texreg = new TextureRegion(projectileTextureList, 144, 0, 48, 48);
		}
	}
	
	public void dispose() {
		texreg = null;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if(this.x<0.0f) {
			batch.draw(texreg,this.x,this.y,this.originX,this.originY,this.width,this.height,-this.scaleX,this.scaleY,this.rotation);
		}
		else {
			batch.draw(texreg,this.x,this.y,this.originX,this.originY,this.width,this.height,this.scaleX,this.scaleY,this.rotation);
		}
	}

	@Override
	public Actor hit(float x, float y) {
		if( x > 0 && x < this.width && y > 0 && y < this.height) {
			return this;
		}
		return null;
	}

	@Override
	public float GetProjectileMaxSpeed() {
		return MAX_SPEED;
	}
	
	
	private final static int EXPLOSION_MARGIN = 30;
	private final static float DURATION_OF_BARREL_EXPLOSION_IN_SECONDS = 1.5f;
	
	public void explodeMaybe(
			final RenderTree theRenderTree,
			Scene theScene
			//ArrayList<DeadFrog> deadFrogs, 
			//ArrayList<Frog> frogs, 
			/*ArrayList<Barrel1> barrels, */ 
			//ArrayList<Projectile> projectiles, 
			//ArrayList<BarrelExplosion> barrelExplosions,
			//widthOfScene,
			//Vector2 positionOfExplosion
	) {
		
//		theScene.deadFrogs, 
//		theScene.frogs, 
//		theScene.projectiles, 
//		theScene.barrelExplosions,
//		theScene.getW(),
		if (
				  (this.x > (0                      +EXPLOSION_MARGIN)) && //a droite de la marge gauche
			      ( (this.x+this.width) < (theScene.getWidth()-EXPLOSION_MARGIN))/* &&*/ //a gauche de la marge droite
			      //(this.y+this.height < (Gdx.graphics.getHeight()-EXPLOSION_MARGIN))&& //en bas de la marge haut 
			      //(this.y > (0                       +EXPLOSION_MARGIN))   //en haut de la marge basse
				){
			System.out.println("BOOOOOUUUUUUM" + "(" + x + "," + y + ")");
			
			theRenderTree.killAllFrog(theScene.deadFrogs, theScene.frogs);
			final BarrelExplosion splosion = new BarrelExplosion(new Vector2(this.x-24, this.y-24));
			theScene.barrelExplosions.add(splosion);
			
			int barrelDestroyedIndex = 0;
			while (barrelDestroyedIndex < theScene.projectiles.size()) {
				if (theScene.projectiles.get(barrelDestroyedIndex).getName().startsWith("projectileBarrel1")) {
					Barrel1 barrelDestroyed = (Barrel1)theScene.projectiles.get(barrelDestroyedIndex);
					if (barrelDestroyed == null) {
						barrelDestroyedIndex++;
						continue;
					}
					theScene.projectiles.remove(barrelDestroyedIndex);
					theRenderTree.getCurrentStage().removeActor(barrelDestroyed);
				}
				else {
					barrelDestroyedIndex++;
				}
			}
			
			theRenderTree.getCurrentStage().addActor(splosion);
			//splosion will disappear from the road
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			                theRenderTree.getCurrentStage().removeActor(splosion);
			            }
			        }, 
			        (long) (DURATION_OF_BARREL_EXPLOSION_IN_SECONDS*1000)
			        
			);
			///THIERR!
//			MyGdxGame.barrelExplosionSFX.play();
			
		} //end of if margins
		else {
			System.out.println("inside of margins"+ "(" + x + "," + y + ")");
			System.out.println("second:" +(this.x+this.width) + "a gauche de " + Integer.toString(theScene.getWidth()-EXPLOSION_MARGIN)
					+ Boolean.toString((this.x+this.width) < (theScene.getWidth()-EXPLOSION_MARGIN)));
			    
			System.out.println("first:" + this.x + "a droite de " + Integer.toString(0                      +EXPLOSION_MARGIN) + ":"
					+ Boolean.toString(this.x > (0 +EXPLOSION_MARGIN)));
		}
			
			
			
			
	} //end of function
}; //end of class
