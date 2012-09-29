package ca.polymtl.ourscureuil;

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
	private RenderTree renderTree;
	private float timeToAddProjectile = 2.0f;
	private float timeOfLife = 10.0f;
	private float actualTimer = 0.0f;  
	private Vector2[] waypointsProjectiles;
	private Boolean[] activeProjectiles = new Boolean[10];
	
	
	public Scene(float scenewidth,float sceneheight, RenderTree rt) {
		
		 java.util.Arrays.fill(activeProjectiles, false);
		 renderTree = rt;
		 setProjectilesWaypoints(scenewidth,sceneheight);
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
	
	private void setProjectilesWaypoints(float scenewidth, float sceneheight)
	{
		waypointsProjectiles = new Vector2[8];
		waypointsProjectiles[0] = new Vector2(scenewidth - 40,sceneheight - 96);
		waypointsProjectiles[1] = new Vector2(scenewidth - 40,sceneheight - 144);
		waypointsProjectiles[2] = new Vector2(scenewidth - 40,sceneheight - 192);
		waypointsProjectiles[3] = new Vector2(scenewidth - 40,sceneheight - 240);
		
		waypointsProjectiles[4] = new Vector2(-8, 48);
		waypointsProjectiles[5] = new Vector2(-8, 96);
        waypointsProjectiles[6] = new Vector2(-8, 144);
        waypointsProjectiles[7] = new Vector2(-8, 192);
		
		/*waypointsProjectiles = new Vector2[8];
		waypointsProjectiles[0] = new Vector2(scenewidth - 40,sceneheight - 96);
		waypointsProjectiles[1] = new Vector2(scenewidth - 40,sceneheight - 144);
		waypointsProjectiles[2] = new Vector2(scenewidth - 40,sceneheight - 192);
		waypointsProjectiles[3] = new Vector2(scenewidth - 40,sceneheight - 240);
		
		waypointsProjectiles[4] = new Vector2(-8, 48);
		waypointsProjectiles[5] = new Vector2(-8, 96);
        waypointsProjectiles[6] = new Vector2(-8, 144);
        waypointsProjectiles[7] = new Vector2(-8, 192);*/
        
		/*waypointsProjectiles = new Vector2[8];
		waypointsProjectiles[0] = new Vector2(((800.0f-48.0f)/800.0f)*scenewidth,((480.0f-96.0f)/480.0f)*sceneheight);
		waypointsProjectiles[1] = new Vector2(((800.0f-48.0f)/800.0f)*scenewidth,((480.0f-144.0f)/480.0f)*sceneheight);
		waypointsProjectiles[2] = new Vector2(((800.0f-48.0f)/800.0f)*scenewidth,((480.0f-192.0f)/480.0f)*sceneheight);
		waypointsProjectiles[3] = new Vector2(((800.0f-48.0f)/800.0f)*scenewidth,((480.0f-240.0f)/480.0f)*sceneheight);
		
		waypointsProjectiles[4] = new Vector2(0.0f, (48.0f/480.0f)*sceneheight);
		waypointsProjectiles[5] = new Vector2(0.0f, (96.0f/480.0f)*sceneheight);
        waypointsProjectiles[6] = new Vector2(0.0f, (144.0f/480.0f)*sceneheight);
        waypointsProjectiles[7] = new Vector2(0.0f, (192.0f/480.0f)*sceneheight);*/
	}
	
	public Actor addProjectile(int idPos, ProjectileType projectileType) {
		
		Stage stage = renderTree.getCurrentStage();
		switch(projectileType) {
			case TRUCK1:
				Truck1 newTruck1 = new Truck1(waypointsProjectiles[idPos]);
				stage.addActor(newTruck1);
				return newTruck1;
			case TRUCK2:
				Truck2 newTruck2 = new Truck2(waypointsProjectiles[idPos]);
				stage.addActor(newTruck2);
				return newTruck2;
			case FASTCAR1:
				FastCar1 newFastCar1 = new FastCar1(waypointsProjectiles[idPos]);
				stage.addActor(newFastCar1);
				return newFastCar1;
			case FASTCAR2:
				FastCar2 newFastCar2 = new FastCar2(waypointsProjectiles[idPos]);
				stage.addActor(newFastCar2);
				return newFastCar2;
			case FASTCAR3:
				FastCar3 newFastCar3 = new FastCar3(waypointsProjectiles[idPos]);
				stage.addActor(newFastCar3);
				return newFastCar3;
			case MOTORCYCLE:
				Motorcycle1 newMotorcycle1 = new Motorcycle1(waypointsProjectiles[idPos]);
				stage.addActor(newMotorcycle1);
				return newMotorcycle1;
			case VAN1:
				Van1 newVan1 = new Van1(waypointsProjectiles[idPos]);
				stage.addActor(newVan1);
				return newVan1;
			case VAN2:
				Van2 newVan2 = new Van2(waypointsProjectiles[idPos]);
				stage.addActor(newVan2);
				return newVan2;
			case COP:
				Cop1 newCop1 = new Cop1(waypointsProjectiles[idPos]);
				stage.addActor(newCop1);
				return newCop1;
			case BARREL:
				Barrel1 newBarrel1 = new Barrel1(waypointsProjectiles[idPos]);
				stage.addActor(newBarrel1);
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
		int xStart 	= (int) (width * 0.2f + randomGenerator.nextInt((int) (width * 0.6f)));
		int vX		= 25+randomGenerator.nextInt(35);
		int vY		= 35+randomGenerator.nextInt(35);
		
			
		final Frog frog = new Frog(new Vector2(xStart,10),"bob");
		renderTree.getCurrentStage().addActor(frog);
		frog.SetMovement(new Vector2(vX,vY));
			
	}
}
