package ca.polymtl.ourscureuil;


import java.util.ArrayList; 

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;



public class LevelBG extends Node {
	
	static int levelId = 0;
	private Texture levelTexture;
	private TextureRegion levelTextureRegion;
	//private Sprite levelSprite;
	
	public LevelBG ( String levelPath ) {
		super ( new Vector2(0.0f,0.0f), new String("level").concat( new Integer(levelId).toString()) );
		levelId++;
		levelTexture = new Texture(Gdx.files.internal(levelPath));
		levelTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		levelTextureRegion = new TextureRegion(levelTexture, 0, 0, 800, 480);
		//levelSprite = new Sprite(levelTextureRegion);
		//levelSprite.setSize(1.0f, 1.0f * levelSprite.getHeight() / levelSprite.getWidth());
		//levelSprite.setOrigin(levelSprite.getWidth()/2, levelSprite.getHeight()/2);
		//levelSprite.setPosition(-levelSprite.getWidth()/2, -levelSprite.getHeight()/2);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		//levelSprite.draw(batch);
		batch.draw(levelTextureRegion,0.0f,0.0f);
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

}
