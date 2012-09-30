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
	private SpriteBatch batch;
	private float deviceWidth;
	private float deviceHeight;
	private TextureRegion lifebarLeftBorder;
	private TextureRegion lifebarRightBorder;
	private TextureRegion lifebarPoint;

	public HUD(float dwidth,float dheight, SpriteBatch batch) {
		this.deviceWidth = dwidth;
		this.deviceHeight = dheight;
		this.batch = batch;
		lifebarTexture = new Texture(Gdx.files.internal("data/lifebar.png"));
		lifebarTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		lifebarLeftBorder = new TextureRegion(lifebarTexture, 	0, 0, 32, 32);
		lifebarRightBorder = new TextureRegion(lifebarTexture, 	32, 0, 32, 32);
		lifebarPoint = new TextureRegion(lifebarTexture, 	0, 32, 32, 32);
		timer = new BitmapFont();
		timer.setScale(2.0f, 2.0f);
		timer.setColor(0.0f,1.0f,0.0f,1.0f);
	}
	
	public void draw(int nRemainingTimeSec) {
		int remainingLifeCount = Score.getInstance().getRemainingLifeCount();
		int totalLifeCount 		= Score.getInstance().getTotalLifeCount();
		
		int seconds = nRemainingTimeSec % 60;
		int minutes = nRemainingTimeSec / 60;
		String time = (minutes < 10 ? "0" : "") + String.valueOf(minutes) + 
				":" + (seconds < 10 ? "0" : "") + String.valueOf(seconds);
		
		batch.begin();
		batch.draw(lifebarLeftBorder, 80, deviceHeight-40);
		batch.draw(lifebarRightBorder, 80+1+(totalLifeCount-1)*(18+2), deviceHeight-40);
		for(int i = 0; i < remainingLifeCount; ++i) {
			batch.draw(lifebarPoint, 80+1+i*(18+2), deviceHeight-40);
		}
		timer.draw(batch, time, deviceWidth-150, deviceHeight-10);
		batch.end();
	}
}
