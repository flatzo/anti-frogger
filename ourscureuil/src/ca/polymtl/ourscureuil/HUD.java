package ca.polymtl.ourscureuil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUD {
	private Texture lifebarTexture;
	private BitmapFont timer;
	private SpriteBatch hudBatch;
	private float w;
	private float h;
	private TextureRegion lifebarLeftBorder;
	private TextureRegion lifebarRightBorder;
	private TextureRegion lifebarPoint;

	public HUD(float w,float h, SpriteBatch batch) {
		this.w = w;
		this.h = h;
		hudBatch = batch;
		lifebarTexture = new Texture(Gdx.files.internal("data/lifebar.png"));
		lifebarTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		lifebarLeftBorder = new TextureRegion(lifebarTexture, 	0, 0, 32, 32);
		lifebarRightBorder = new TextureRegion(lifebarTexture, 	32, 0, 32, 32);
		lifebarPoint = new TextureRegion(lifebarTexture, 	0, 32, 32, 32);
		timer = new BitmapFont();
		timer.setScale(2.0f, 2.0f);
		timer.setColor(0.0f,1.0f,0.0f,1.0f);
	}
	
	public void draw(int nCurrLife, int nTotLife, int nRemainingTimeSec) {
		int seconds = nRemainingTimeSec % 60;
		int minutes = nRemainingTimeSec / 60;
		String time = String.valueOf(minutes) + ":" + String.valueOf(seconds);
		
		hudBatch.begin();
		hudBatch.draw(lifebarLeftBorder, 80, h-40);
		hudBatch.draw(lifebarRightBorder, 80+1+(nTotLife-1)*(18+2), h-40);
		for(int i = 0; i < nCurrLife; ++i) {
			hudBatch.draw(lifebarPoint, 80+1+i*(18+2), h-40);
		}
		timer.draw(hudBatch, time, w-150, h-10);
		hudBatch.end();
	}
}
