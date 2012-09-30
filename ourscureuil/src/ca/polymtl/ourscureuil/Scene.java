package ca.polymtl.ourscureuil;

import java.util.ArrayList;
import java.util.Random;


import ca.polymtl.ourscureuil.Projectile.ProjectileType;
import ca.polymtl.ourscureuil.projectiles.Barrel1;
import ca.polymtl.ourscureuil.projectiles.Cop1;
import ca.polymtl.ourscureuil.projectiles.FastCar1;
import ca.polymtl.ourscureuil.projectiles.FastCar2;
import ca.polymtl.ourscureuil.projectiles.FastCar3;
import ca.polymtl.ourscureuil.projectiles.Motorcycle1;
import ca.polymtl.ourscureuil.projectiles.Truck1;
import ca.polymtl.ourscureuil.projectiles.Truck2;
import ca.polymtl.ourscureuil.projectiles.Van1;
import ca.polymtl.ourscureuil.projectiles.Van2;

import ca.polymtl.ourscureuil.CarCrashSmoke;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.LibgdxToolkit;
import com.sun.xml.internal.ws.api.message.Message;

public class Scene {
	private int mWidth;
	private int h;
	private RenderTree renderTree;
	private float timeToAddProjectile = 2.0f;
	private float timeOfLife = 10.0f;
	private float actualTimer = 0.0f;  
	private Vector2[] waypointsProjectiles;
	private Boolean[] activeProjectiles = new Boolean[10];
	
	
	public ArrayList<Frog> frogs = new ArrayList<Frog>();
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public ArrayList<DeadFrog> deadFrogs = new ArrayList<DeadFrog>();
	//public ArrayList<Barrel1> barrels = new ArrayList<Barrel1>();
	public ArrayList<CarCrashSmoke> carCrashSmokeClouds = new ArrayList<CarCrashSmoke>();
	public ArrayList<BarrelExplosion> barrelExplosions = new ArrayList<BarrelExplosion>();
	
	
	public Scene(int w,int h, RenderTree rt) {
		System.out.println("w:" + Integer.toString(w));
		 java.util.Arrays.fill(activeProjectiles, false);
		 renderTree = rt;
		 setProjectilesWaypoints(w,h);
		 this.mWidth = w;
	}
	
	public void dispose() {
		renderTree.dispose();
	}
	
	public void draw()
	{
		actualTimer += Gdx.graphics.getDeltaTime();
		
		if(actualTimer > timeToAddProjectile)
		{
			spawnRandomFrog();
			SpawnRandomProjectiles();
			actualTimer = 0.0f;
		}
		
	}
	
	private void setProjectilesWaypoints(float w, float h)
	{
		waypointsProjectiles = new Vector2[8];
		waypointsProjectiles[0] = new Vector2(w - 40,h - 96);
		waypointsProjectiles[1] = new Vector2(w - 40,h - 144);
		waypointsProjectiles[2] = new Vector2(w - 40,h - 192);
		waypointsProjectiles[3] = new Vector2(w - 40,h - 240);
		
		waypointsProjectiles[4] = new Vector2(0, 48);
		waypointsProjectiles[5] = new Vector2(0, 96);
        waypointsProjectiles[6] = new Vector2(0, 144);
        waypointsProjectiles[7] = new Vector2(0, 192);
        
        // add waypoints for counter-traffic?
	}
	
	public Actor addProjectile(int idPos, ProjectileType projectileType) {
		
		Stage stage = renderTree.getCurrentStage();
		//ProjectileType debug = ProjectileType.BARREL;
		//switch(debug) {
		switch(projectileType) {
			case TRUCK1:
				Truck1 newTruck1 = new Truck1(waypointsProjectiles[idPos]);
				stage.addActor(newTruck1);
				projectiles.add(newTruck1);
				return newTruck1;
			case TRUCK2:
				Truck2 newTruck2 = new Truck2(waypointsProjectiles[idPos]);
				stage.addActor(newTruck2);
				projectiles.add(newTruck2);
				return newTruck2;
			case FASTCAR1:
				FastCar1 newFastCar1 = new FastCar1(waypointsProjectiles[idPos]);
				stage.addActor(newFastCar1);
				projectiles.add(newFastCar1);
				return newFastCar1;
			case FASTCAR2:
				FastCar2 newFastCar2 = new FastCar2(waypointsProjectiles[idPos]);
				stage.addActor(newFastCar2);
				projectiles.add(newFastCar2);
				return newFastCar2;
			case FASTCAR3:
				FastCar3 newFastCar3 = new FastCar3(waypointsProjectiles[idPos]);
				stage.addActor(newFastCar3);
				projectiles.add(newFastCar3);
				return newFastCar3;
			case MOTORCYCLE:
				Motorcycle1 newMotorcycle1 = new Motorcycle1(waypointsProjectiles[idPos]);
				stage.addActor(newMotorcycle1);
				projectiles.add(newMotorcycle1);
				return newMotorcycle1;
			case VAN1:
				Van1 newVan1 = new Van1(waypointsProjectiles[idPos]);
				stage.addActor(newVan1);
				projectiles.add(newVan1);
				return newVan1;
			case VAN2:
				Van2 newVan2 = new Van2(waypointsProjectiles[idPos]);
				stage.addActor(newVan2);
				projectiles.add(newVan2);
				return newVan2;
			case COP:
				Cop1 newCop1 = new Cop1(waypointsProjectiles[idPos]);
				stage.addActor(newCop1);
				projectiles.add(newCop1);
				return newCop1;
			case BARREL:
//				System.out.println("idPos:" +idPos);
//				if (idPos >= 8) {
//					System.out.println("idPos random too high");
//				}
//				System.out.println("wpp" + waypointsProjectiles[idPos]);
				Barrel1 newBarrel1 = new Barrel1(waypointsProjectiles[idPos]);
				stage.addActor(newBarrel1);
				projectiles.add(newBarrel1);
				return newBarrel1;
			default:
			return null;
		}

	}
	
	private void SpawnRandomProjectiles()
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(8);
		int randomCar = randomGenerator.nextInt(10);
		int counter = 0;
		boolean isActive = false;
		
		while( counter < 8 && !isActive)
		{
			/*
			//Rotate actor
			if(randomInt > 4)
			{
				car.action(RotateBy.$(180f,0.0f));
				
				if((car.width-40)>0)
					car.action(MoveBy.$((car.width-40),0.0f,0.0f));
			}
			if((car.width - 40) > 0)
				car.action(MoveBy.$(-(car.width - 40),0.0f,0.0f));
			*/
			
			Vector2 waypoint = waypointsProjectiles[randomInt];
			
			if(activeProjectiles[randomInt] == false)
			{
				
				final Actor car = addProjectile(randomInt, ProjectileType.values()[randomCar]);
				isActive = true;
				activeProjectiles[randomInt] = true;
				final int indexToDelete = randomInt;
				
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				                renderTree.getCurrentStage().removeActor(car);
				                activeProjectiles[indexToDelete] = false;
				            }
				        }, 
				        (long) (timeOfLife*1000) 
				);
			}
			counter++;
			randomInt = randomGenerator.nextInt(8);
			
		}
		
		
	}
	
	private void spawnRandomFrog() {
		float width = Gdx.graphics.getWidth();
		Random randomGenerator = new Random();
		int xStart 	= (int) (100 + randomGenerator.nextInt(600));
		int vX		= 25+randomGenerator.nextInt(35);
		int vY		= 35+randomGenerator.nextInt(35);
		
			
		final Frog frog = new Frog(new Vector2(xStart,10),"bob");
		renderTree.getCurrentStage().addActor(frog);
		frogs.add(frog);
		frog.SetMovement(new Vector2(vX,vY));
		
	}
	
	public int getWidth() {
		//System.out.println("w:" + Integer.toString(this.mWidth));
		return this.mWidth;
	}
}
