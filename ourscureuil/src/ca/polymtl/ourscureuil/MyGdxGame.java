package ca.polymtl.ourscureuil;

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
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private SpriteBatch hudBatch;
	private HUD hud;
	private InputResponse inputResponse = new InputResponse();
	private Scene scene;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		inputResponse.registerWidthHeight(w,h);
		Gdx.input.setInputProcessor(new GestureDetector(inputResponse));
		hud = new HUD(w,h,batch);
		renderTree = new RenderTree(w,h,true,batch);
		
		inputResponse.registerRenderTree(renderTree);
		
		//Spawn les projectiles
		scene = new Scene((int)w,(int)h,renderTree);
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
		
		//batch.setProjectionMatrix(camera.combined);
		renderTree.getCurrentStage().act(Gdx.app.getGraphics().getDeltaTime());
		renderTree.draw();	
		hud.draw(5, 8, 75);
		
		scene.draw();
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
