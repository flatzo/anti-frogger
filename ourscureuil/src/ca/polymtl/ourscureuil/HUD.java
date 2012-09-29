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
	private int w,h;
	private TextureRegion emptyLifebarLeft;
	private TextureRegion emptyLifebarRight;
	private TextureRegion emptyLifebarCenter;
	private TextureRegion lifebarLeft;
	private TextureRegion lifebarRight;
	private TextureRegion lifebarCenter;
	private final int maxLife = 5;

	public HUD(int w,int h) {
		this.w = w;
		this.h = h;
		hudBatch = new SpriteBatch();
		
		lifebarTexture = new Texture(Gdx.files.internal("data/lifebar.png"));
		lifebarTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		emptyLifebarLeft = new TextureRegion(lifebarTexture, 	0, 0, 32, 32);
		emptyLifebarCenter = new TextureRegion(lifebarTexture, 	32, 0, 32, 32);
		emptyLifebarRight = new TextureRegion(lifebarTexture, 	64, 0, 32, 32);
		
		lifebarLeft = new TextureRegion(lifebarTexture, 	0, 32, 32, 32);
		lifebarCenter = new TextureRegion(lifebarTexture, 	32, 32, 32, 32);
		lifebarRight = new TextureRegion(lifebarTexture, 	64, 32, 32, 32);
		
		
		timer = new BitmapFont();
		timer.setColor(0.0f,0.0f,0.0f,1.0f);
	}
	
	public void draw(int life, int remainingTime) {
		int seconds = remainingTime % 60;
		int minutes = remainingTime / 60;
		String time = String.valueOf(minutes) + ":" + String.valueOf(seconds);
		
		hudBatch.begin();
			drawLifeBar(5);
			timer.draw(hudBatch, time, w - 100,h-10);
		hudBatch.end();
	}
	
	private void drawLifeBar(int life) {
		for(int i = 0; i < maxLife; ++i) {
			switch(i) {
			case 0: // premier
				if(life == 0) {
					hudBatch.draw(emptyLifebarLeft, 	100, h-10-32);
				} else {
					hudBatch.draw(lifebarLeft, 			100, h-10-32);
				}
				break;
			case maxLife-1:
				if(life == maxLife) {
					hudBatch.draw(lifebarRight, 		100 + i * 32, h-10-32);
				} else {
					hudBatch.draw(emptyLifebarRight, 	100 + i * 32, h-10-32);
				}
				break;
			default:
				if(life > i) {
					hudBatch.draw(lifebarCenter, 		100 + i * 32, h-10-32);
				} else {
					hudBatch.draw(emptyLifebarCenter, 	100 + i * 32, h-10-32);
				}
				break;	
			}
		}
	}
}
