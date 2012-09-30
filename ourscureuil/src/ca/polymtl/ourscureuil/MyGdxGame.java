package ca.polymtl.ourscureuil;

import java.util.ArrayList;

import ca.polymtl.ourscureuil.Projectile.ProjectileType;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MyGdxGame implements ApplicationListener {
	
	private RenderTree renderTree;
	private SpriteBatch batch;
	private HUD hud;
	private InputResponse inputResponse;
	private Scene scene;
	private float remainingTime;
	
	@Override
	public void create() {	
		remainingTime = Score.getInstance().getMaxTime();
		float deviceWidth = Gdx.graphics.getWidth();
		float deviceHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		hud = new HUD(800,480,batch);
		renderTree = new RenderTree(800,480,true,batch);
		inputResponse = new InputResponse();
		inputResponse.registerRenderTree(renderTree);
		Gdx.input.setInputProcessor(new GestureDetector(inputResponse));
				
		//Spawn les objets pour le level 1
		//scene = new Scene(deviceWidth,deviceHeight,renderTree);
		scene = new Scene(800,480,renderTree);
		renderTree.getCurrentStage().addActor(new LevelBG("data/level1v2_1024.png"));
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {	
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		renderTree.reactCollisionFrogs(scene.frogs, scene.projectiles, scene.deadFrogs);
		renderTree.reactCollisionProjectiles(scene.projectiles, scene.carCrashSmokeClouds);
		
		//batch.setProjectionMatrix(camera.combined);
		float deltaTime = Gdx.app.getGraphics().getDeltaTime();
		remainingTime -= deltaTime;
		
		 if(Score.getInstance().isGameOver()) {
				// TODO Afficher game over
				System.out.println("Game over");
		 } else	if(remainingTime >= 0.0f) {
			renderTree.getCurrentStage().act(deltaTime);
			renderTree.draw();	
			hud.draw((int) remainingTime);
			
			scene.draw();
		} else {
			// TODO AFFICHER GAGNANT
		}
		
	}

	@Override
	public void resize(int width, int height)
	{
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	
	
}
