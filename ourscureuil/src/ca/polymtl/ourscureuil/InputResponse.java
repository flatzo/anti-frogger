package ca.polymtl.ourscureuil;

import ca.polymtl.ourscureuil.*;
import ca.polymtl.ourscureuil.MyGdxGame.GameState;
import ca.polymtl.ourscureuil.projectiles.Barrel1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;


public class InputResponse implements GestureListener {

	Projectile mSelectedProjectile = null; //can be null
	RenderTree theRenderTree;
	Scene theScene;
//	GameState theGameState = MyGdxGame.GameState.PLAYING;
	
	InputResponse() {
		super();
	}
	
	void registerRenderTree(RenderTree renderTree) {
		theRenderTree = renderTree;
	}
	void registerScene(Scene scene) {
		theScene = scene;
	}
//	void registerState(MyGdxGame.GameState gameState) {
//		
//	}
	
	
	void selectProjectileAtPosition(int x, int y) {
		if (theRenderTree == null) {
			System.out.println("ddd");
		}
		Actor actorFound = theRenderTree.getCurrentStage().hit(x*800/Gdx.graphics.getWidth(),
																(Gdx.graphics.getHeight()-y)*480/Gdx.graphics.getHeight());
		if (actorFound == null) {
			System.out.println("no actor found with hit method");
			mSelectedProjectile = null;
			return;
		}
		
		//Actor dd = new Actor("ddd");
		System.out.println(actorFound.name);
		System.out.println(actorFound.originX);
		System.out.println(actorFound.originY);
		System.out.println(actorFound.visible);
		System.out.println(actorFound.height); //marche
		System.out.println(actorFound.width); //marche
		System.out.println(actorFound.x); //marche , toujours null
		System.out.println(actorFound.y); //marche
		//System.out.println(actorFound.touchDragged(x, y, pointer));
		//System.out.println(actorFound.);
		
		String nameOfSelected = actorFound.name;
		String projectilePrefix = "projectile";
		if (!nameOfSelected.startsWith(projectilePrefix)) {
			//pas d/placable
			mSelectedProjectile = null;
			System.out.println("no actor found with name projectile");
			return;
		}
		
		// si on arrive ici, alors on sait que l'actor choisit est un projectile
		mSelectedProjectile = (Projectile)actorFound;
		
		
		//bang on Barrel
		
		//remove projectileprefix
		nameOfSelected = nameOfSelected.substring(projectilePrefix.length()); //beginning index is included, 
		if (nameOfSelected.startsWith("Barrel1") ) {
			Barrel1 mSelectedBarrel = (Barrel1) mSelectedProjectile;
			if (mSelectedBarrel != null) {
				System.out.println("width:" + Integer.toString(theScene.getWidth()));
				mSelectedBarrel.explodeMaybe(
						theRenderTree, 
						theScene
						); //48 est la moitié de largeur et hauteur
			}
		}
		
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer) {

		//check mute/unmute
		System.out.println("touchdown");
		System.out.println("finger:" + Double.toString((float)(x*800/Gdx.graphics.getWidth())) + "," + Double.toString(((((float)y)-Gdx.graphics.getHeight())*480/Gdx.graphics.getHeight())) + ",height:"+ HUD.muteButtonHeight
				+ ",width" + HUD.muteButtonWidth);
		System.out.println("bouton:" + Double.toString(HUD.muteButtonX) + "," + Double.toString(HUD.muteButtonY) + ",height:"+ HUD.muteButtonHeight
				+ ",width" + HUD.muteButtonWidth);
		if (theRenderTree.intersectionRectangleRectangle(new Vector2((float)(x*800/Gdx.graphics.getWidth()), ((Gdx.graphics.getHeight()-((float)y))*480/Gdx.graphics.getHeight())), HUD.muteButtonHeight, HUD.muteButtonWidth, new Vector2(HUD.muteButtonX, HUD.muteButtonY), HUD.muteButtonHeight, HUD.muteButtonWidth)) {
			MyGdxGame.isMuted = !MyGdxGame.isMuted;
			if (MyGdxGame.isMuted) {
				MyGdxGame.playingMusic.stop();
				MyGdxGame.gameOverMusic.stop();
				MyGdxGame.victoryMusic.stop();
			}
			else {
				if (MyGdxGame.gameState == GameState.PLAYING) {
					MyGdxGame.playingMusic.play();
				}
				else if (MyGdxGame.gameState == GameState.VICTORY) {
					MyGdxGame.victoryMusic.play();
				}
				else if (MyGdxGame.gameState == GameState.GAME_OVER) {
					MyGdxGame.gameOverMusic.play();
				}
			}
			System.out.println("intersetion");
		}
		else {
			System.out.println("no intersection");
		}
				
		// origine: en haut a gauche
		// note: les coords fournies ici sont donnees sur l'ecran de la device utilisee; il faut donc rescaler a 800x480
		selectProjectileAtPosition(x, y);
		return false;
	}

	@Override
	public boolean tap(int x, int y, int count) {
		// TODO Auto-generated method stub
		
		if (MyGdxGame.gameState == GameState.GAME_OVER) {
			MyGdxGame.gameState = GameState.NEW_GAME;
		}
		else if (MyGdxGame.gameState == GameState.VICTORY) {
			MyGdxGame.gameState = GameState.NEW_GAME;
		}
		
		return false;
	}

	@Override
	public boolean longPress(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY) {
		if (MyGdxGame.gameState == GameState.PLAYING) {
			if(mSelectedProjectile!=null) {
				mSelectedProjectile.SetMovement(new Vector2(velocityX,velocityY));
				System.out.printf("\nFLIIIIIIIIIIIIIIIIIIIIIING\n");
				return true;
			}
		}
		else if (MyGdxGame.gameState == GameState.GAME_OVER) {
			MyGdxGame.gameState = GameState.NEW_GAME;
		}
		else if (MyGdxGame.gameState == GameState.VICTORY) {
			MyGdxGame.gameState = GameState.NEW_GAME;
		}

		return false;
	}

	@Override
	public boolean pan(int x, int y, int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float originalDistance, float currentDistance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
		// TODO Auto-generated method stub
		return false;
	}

}
