package ca.polymtl.ourscureuil;

import ca.polymtl.ourscureuil.*;

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


public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private SpriteBatch hudBatch;
	private Texture texture;
	private Sprite sprite;
	private HUD hud;
	private HUD posx;
	private HUD posy;
	//private TheRenderTree renderTree; 
	
	
	private MyInteger time = new MyInteger(0);
	private MyInteger positionX = new MyInteger(0);
	private MyInteger positionY = new MyInteger(0);
	
	private MyGestureListener gestureListener = new MyGestureListener(time, positionX, positionY);
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		hud = new HUD((int)w,(int)h);
		posx = new HUD((int)w,(int)h);
		posy = new HUD((int)w,(int)h);
		
		texture = new Texture(Gdx.files.internal("data/level1v2_1024.png"));
		
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region = new TextureRegion(texture, 0, 0, 800, 480);
		
		sprite = new Sprite(region);
		sprite.setSize(1.0f, 1.0f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		Gdx.input.setInputProcessor(new GestureDetector(gestureListener));
		
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
		
		
		hud.draw(0, time.getI(), 0);
		//posx.draw(0, time.getI(), 30);
		//posy.draw(0, time.getI(), -30);
		posx.printNumber(78, -30, 0);
		posy.print("ert", 30, 0);
		
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
