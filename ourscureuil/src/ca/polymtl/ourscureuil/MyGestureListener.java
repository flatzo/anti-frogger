package ca.polymtl.ourscureuil;

import ca.polymtl.ourscureuil.*;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;



public class MyGestureListener implements GestureListener {

	MyInteger counter;
	MyInteger mPositionX;
	MyInteger mPositionY;
	
	MyGestureListener(MyInteger time,MyInteger positionX, MyInteger positionY) {
		super();
		counter = time;
		mPositionX = positionX;
		mPositionY = positionY;
	}
	
	void incre() {
		counter.incre();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		//counter.incre();
		mPositionX.setI(x);
		mPositionY.setI(y);
		
		return false;
	}

	@Override
	public boolean tap(int x, int y, int count) {
		// TODO Auto-generated method stub
		//counter.incre();
		return false;
	}

	@Override
	public boolean longPress(int x, int y) {
		// TODO Auto-generated method stub
		//counter.incre();
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		counter.incre();
		return false;
	}

	@Override
	public boolean pan(int x, int y, int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		//counter.incre();
		return false;
	}

	@Override
	public boolean zoom(float originalDistance, float currentDistance) {
		// TODO Auto-generated method stub
		//counter.incre();
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialFirstPointer,
			Vector2 initialSecondPointer, Vector2 firstPointer,
			Vector2 secondPointer) {
		// TODO Auto-generated method stub
		//counter.incre();
		return false;
	}

}
