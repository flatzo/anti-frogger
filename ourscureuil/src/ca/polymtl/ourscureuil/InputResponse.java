package ca.polymtl.ourscureuil;

import ca.polymtl.ourscureuil.*;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;



public class InputResponse implements GestureListener {

	//MyInteger counter;
	//MyInteger mPositionX;
	//MyInteger mPositionY;
	
	//int currentFingerPositionX = 0;
	//int currentFingerPositionY = 0;
	Projectile mSelectedProjectile = null; //can be null
	
	RenderTree theRenderTree;
	
	InputResponse(RenderTree renderTree) {
		super();
		//counter = time;
		//mPositionX = positionX;
		//mPositionY = positionY;
		
		theRenderTree = renderTree;
		
	}
	
	
	//modifies mSelectedProjectile
	//void findProjectileAtPosition(int x, int y ) {
		
	//}
	
	void selectProjectileAtPosition(int x, int y) {
		Actor actorFound = theRenderTree.getStage().hit(x, y);
		if (actorFound == null) {
			mSelectedProjectile = null;
			return;
		}
		if (!actorFound.name.startsWith("projectile")) {
			//pas d/placable
			mSelectedProjectile = null;
			return;
		}
		
		mSelectedProjectile = theRenderTree.getProjectile(actorFound); //avoir verifier que c<est un projectile avant
		
	}

	void moveSelectedAtSpeed(float velocityX, float velocityY) {
		Vector2 newDistancePerRender = new Vector2(velocityX,velocityY);
		//adapt here , increase, decrease
		
		//mettre du clipping aux voies
		
		mSelectedProjectile.setDistancePerRender(newDistancePerRender);
	}
	
	
	
	@Override
	public boolean touchDown(int x, int y, int pointer) {

		//xa gauche
		//y en haut
		
		return false;
	}

	@Override
	public boolean tap(int x, int y, int count) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		
		//counter.incre();
		moveSelectedAtSpeed(velocityX, velocityY);
		
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
	public boolean pinch(Vector2 initialFirstPointer,
			Vector2 initialSecondPointer, Vector2 firstPointer,
			Vector2 secondPointer) {
		// TODO Auto-generated method stub
		return false;
	}

}
