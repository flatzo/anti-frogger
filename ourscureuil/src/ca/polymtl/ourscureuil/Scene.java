package ca.polymtl.ourscureuil;

import java.util.Random;


import ca.polymtl.ourscureuil.Projectile.ProjectileType;
import ca.polymtl.ourscureuil.projectiles.Truck1;

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
	private int w;
	private int h;
	private RenderTree renderTree;
	private Boolean[] activeProjectiles = new Boolean[10];
	private float timeToAddProjectile = 2.0f;
	private float timeOfLife = 10.0f;
	private float actualTimer = 0.0f;  
	
	public Scene(int w,int h, RenderTree rt) {
		
		 java.util.Arrays.fill(activeProjectiles, false);
		 renderTree = rt;
	}
	
	public void draw()
	{
		actualTimer += Gdx.graphics.getDeltaTime();
		
		if(actualTimer > timeToAddProjectile)
		{
			
			SpawnRandomProjectiles();
			actualTimer = 0.0f;
			
			
		}
	}
	
	private void SpawnRandomProjectiles()
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(8);
		int counter = 0;
		boolean isActive = false;
		
		while( counter < 8 && !isActive)
		{
		
			Vector2 waypoint = renderTree.getWayPoints()[randomInt];
			
			if(renderTree.getCurrentStage().hit(waypoint.x, waypoint.y) == null)
			{
				final Truck1 truck1 = new Truck1(renderTree.getWayPoints()[randomInt]);
				renderTree.getCurrentStage().addActor(truck1);
				isActive = true;
				
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				                renderTree.getCurrentStage().removeActor(truck1);
				                
				            }
				        }, 
				        (long) (timeOfLife*1000) 
				);
			}
			counter++;
			randomInt = randomGenerator.nextInt(8);
			
		}
		
		
	}
}
