package ca.polymtl.ourscureuil;

import ca.polymtl.ourscureuil.*;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;


public class InputResponse implements GestureListener {

	//MyInteger counter;
	//MyInteger mPositionX;
	//MyInteger mPositionY;
	
	//int currentFingerPositionX = 0;
	//int currentFingerPositionY = 0;
	Projectile mSelectedProjectile = null; //can be null
	Actor mSelectedActor = null;
	float mHeight = 0;
	float mWidth = 0;
	
	RenderTree theRenderTree;
	
	
	
	InputResponse() {
		super();
	}
	
	void registerRenderTree(RenderTree renderTree) {
		theRenderTree = renderTree;
	}
	
	void registerWidthHeight(float w, float h) {
		mWidth = w;
		mHeight = h;
	}
	
	//modifies mSelectedProjectile
	//void findProjectileAtPosition(int x, int y ) {
		
	//}
	
	void selectProjectileAtPosition(int x, int y) {
		if (theRenderTree == null) {
			System.out.println("ddd");
		}
		Actor actorFound = theRenderTree.getStage().hit(x, y);
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
		
		if (!actorFound.name.startsWith("projectile")) {
			//pas d/placable
			mSelectedProjectile = null;
			System.out.println("no actor found with name projectile");
			return;
		}
		
		mSelectedProjectile = theRenderTree.getProjectile(actorFound); //avoir verifier que c<est un projectile avant
		mSelectedActor = actorFound;
		
	}

	void moveSelectedAtSpeed(float velocityX, float velocityY) {
		Vector2 newDistancePerRender = new Vector2(velocityX,velocityY);
		
		
		//adapt here , increase, decrease
		
		//mettre du clipping aux voies
		if (mSelectedProjectile == null) {
			System.out.println("no projectile selected");
		}
		else {
			mSelectedProjectile.setDistancePerRender(newDistancePerRender);
			System.out.println("distance per render set" + Float.toString(velocityX) +  " ; " + Float.toString(velocityY));
		}
		
		if (mSelectedActor == null) {
			System.out.println("no actor selected");
		}
		else {
			MoveTo action = MoveTo.$((float)mWidth, (float) mHeight, (float)45);
			mSelectedActor.action(action);
		}
	}
	
	
	
	@Override
	public boolean touchDown(int x, int y, int pointer) {

		//xa gauche
		//y en haut
		selectProjectileAtPosition(x, y);
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
