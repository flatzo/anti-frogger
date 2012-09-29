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
	private Texture texture;
	private Sprite sprite;
	private HUD hud;
	private Texture projectileTexture;

	private InputResponse inputResponse = new InputResponse();
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		inputResponse.registerWidthHeight(w,h);
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		hud = new HUD((int)w,(int)h);
		
		// background (level 1)
		texture = new Texture(Gdx.files.internal("data/level1v2_1024.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion region = new TextureRegion(texture, 0, 0, 800, 480);
		sprite = new Sprite(region);
		sprite.setSize(1.0f, 1.0f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		Gdx.input.setInputProcessor(new GestureDetector(inputResponse));
		
		//Create the render tree
		renderTree = new RenderTree();
		
		inputResponse.registerRenderTree(renderTree);
		
		//Spawn les projectiles
		SpawnProjectiles();
		
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
	
		

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			sprite.draw(batch);
		batch.end();
		
		
		
		//renderTree.draw
		hud.draw(5, 8, 75);

		
		renderTree.getStage().act(Gdx.app.getGraphics().getDeltaTime());
		//renderTree.getStage().draw(); //appeler dans la ligne renderTree.draw();
		renderTree.draw();
		

		
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
		renderTree.addProjectile(2,ProjectileType.TAXI);
		renderTree.addProjectile(3,ProjectileType.TRUCK);
		renderTree.addProjectile(4,ProjectileType.SPEEDCAR);

		renderTree.addProjectile(5,ProjectileType.SPEEDCAR);
		renderTree.addProjectile(6,ProjectileType.SPEEDCAR);
		renderTree.addProjectile(7,ProjectileType.SPEEDCAR);
		renderTree.addProjectile(8,ProjectileType.SPEEDCAR);
	}
	
}
