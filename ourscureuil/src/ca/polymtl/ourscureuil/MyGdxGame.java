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
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		inputResponse.registerWidthHeight(w,h);
		Gdx.input.setInputProcessor(new GestureDetector(inputResponse));
		//this.camera = new OrthographicCamera(h, w);
        //this.camera.position.set(w/2, h/2, 0f);
		hud = new HUD(w,h,batch);
		renderTree = new RenderTree(w,h,false,batch);
		
		inputResponse.registerRenderTree(renderTree);
		
		//Spawn les projectiles
		renderTree.getCurrentStage().addActor(new LevelBG("data/level1v2_1024.png"));
		SpawnProjectiles();
			
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
	
	private void SpawnProjectiles()
	{
		//Devrait etre dans l'init de la classe game
		renderTree.addProjectile(1,ProjectileType.COP);
		renderTree.addProjectile(2,ProjectileType.FASTCAR1);
		renderTree.addProjectile(3,ProjectileType.FASTCAR2);
		renderTree.addProjectile(4,ProjectileType.FASTCAR3);

		renderTree.addProjectile(5,ProjectileType.TRUCK1);
		renderTree.addProjectile(6,ProjectileType.TRUCK2);
		renderTree.addProjectile(7,ProjectileType.MOTORCYCLE);
		renderTree.addProjectile(8,ProjectileType.VAN1);
	}
	
}
