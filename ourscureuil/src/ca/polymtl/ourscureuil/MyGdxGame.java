package ca.polymtl.ourscureuil;

import java.util.ArrayList;

import android.R;
import android.media.MediaPlayer;
import ca.polymtl.ourscureuil.Projectile.ProjectileType;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

	private LevelBG gameLevelBG;
	private LevelBG gameOverBG;
	private LevelBG victoryScreenBG;

	// private MediaPlayer mPlay;
	Audio audioPlayer = Gdx.audio;
	Sound scoundrelSFX;
	final float TIME_OF_NEW_GAME_SFX = 3f;
	Sound newGameSFX;
	public static Sound squishSFX;
	public static Music victoryMusic;
	public static Music playingMusic;
	public static Music gameOverMusic;
	public static Sound barrelExplosionSFX;
	public static Sound carCrashSFX;

	public enum GameState {
		PLAYING, GAME_OVER, VICTORY, NEW_GAME
	};

	public static GameState gameState = GameState.PLAYING;

	@Override
	public void create() {
		remainingTime = Score.getInstance().getMaxTime();
		float deviceWidth = Gdx.graphics.getWidth();
		float deviceHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		hud = new HUD(800, 480, batch);
		renderTree = new RenderTree(800, 480, true, batch);
		inputResponse = new InputResponse();
		inputResponse.registerRenderTree(renderTree);
		Gdx.input.setInputProcessor(new GestureDetector(inputResponse));

		// Spawn les objets pour le level 1
		// scene = new Scene(deviceWidth,deviceHeight,renderTree);
		scene = new Scene(800, 480, renderTree);
		inputResponse.registerScene(scene);
		// gameLevelBG = new LevelBG("data/level1v2_1024.png"); //voies
		gameLevelBG = new LevelBG("data/level1v3_1024.png"); // voies qc/ca
		gameOverBG = new LevelBG("data/gameOverBG_1024.png");
		victoryScreenBG = new LevelBG("data/victoryScreen_1024.png");
		// gameOverBG = new LevelBG("data/level1v2_1024.png");
		renderTree.getCurrentStage().addActor(gameLevelBG);

		// audioPlayer.newSound(fileHandle)
		scoundrelSFX = Gdx.audio.newSound(Gdx.files
				.internal("data/scoundrel.mp3"));
		newGameSFX = Gdx.audio.newSound(Gdx.files.internal("data/newgame.mp3"));
		squishSFX = Gdx.audio.newSound(Gdx.files.internal("data/dead_frog.wav"));
		victoryMusic = Gdx.audio.newMusic(Gdx.files.internal("data/victory.mp3"));
		playingMusic = Gdx.audio.newMusic(Gdx.files.internal("data/loop.mp3"));
		gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("data/gameover.mp3"));
		//barrelExplosionSFX = Gdx.audio.newSound(Gdx.files.internal("data/barrelExplosion.wav"));
		//carCrashSFX = Gdx.audio.newSound(Gdx.files.internal("data/carcrash.wav"));

		// scoundrelSFX.play();
		newGameSFX.play();
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				MyGdxGame.playingMusic.play();
			}
		}, (long) (TIME_OF_NEW_GAME_SFX * 1000));

		// mPlay = MediaPlayer.create(this, R.raw.newgame);
		// mPlay.start();

		// SoundManager.loadSounds();

		// SoundManager.playSound(0, 1);

		//
		// SoundManager.playSound(1, 1);
		// new java.util.Timer().schedule(
		// new java.util.TimerTask() {
		// @Override
		// public void run() {
		// SoundManager.stopSound(0);
		// }
		// },
		// (long) (1*1000)
		// );

	}

	@Override
	public void dispose() {
		batch.dispose();
		// mPlay.stop();
		// mPlay.release();
		// mPlay = null ;
	}

	@Override
	public void render() {
		// Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderTree.reactCollisionFrogs(scene.frogs, scene.projectiles,
				scene.deadFrogs);
		renderTree.reactCollisionProjectiles(scene.projectiles,
				scene.carCrashSmokeClouds);

		// batch.setProjectionMatrix(camera.combined);
		float deltaTime = Gdx.app.getGraphics().getDeltaTime();
		remainingTime -= deltaTime;

		switch (gameState) {
		case PLAYING:
			if (Score.getInstance().isGameOver()) {
				renderTree.getCurrentStage().act(deltaTime);
				renderTree.getCurrentStage().addActor(gameOverBG);
				renderTree.getCurrentStage().removeActor(gameLevelBG);
				hud.draw(0);

				// scene.draw();
				renderTree.draw();
				playingMusic.stop();
				gameOverMusic.play();

				gameState = GameState.GAME_OVER;
				// swipe to continue?
				// Afficher game over //thierry à la rescousse
				// System.out.println("Game over");
			} else if (remainingTime >= 0.0f) {
				renderTree.getCurrentStage().act(deltaTime);
				renderTree.draw();
				hud.draw((int) remainingTime);

				scene.draw();
			} else {
				// AFFICHER GAGNANT thierry a la rescousse
				renderTree.getCurrentStage().act(deltaTime);
				renderTree.getCurrentStage().addActor(victoryScreenBG);
				renderTree.getCurrentStage().removeActor(gameLevelBG);
				hud.draw(0);

				// scene.draw();
				renderTree.draw();

				playingMusic.stop();
				victoryMusic.play();

				gameState = GameState.VICTORY;
				// swipe to continue?
			}
			break;
		case GAME_OVER:
			hud.draw(0);
			renderTree.draw();
			break;
		case NEW_GAME:
			remainingTime = Score.getInstance().getMaxTime();
			renderTree = new RenderTree(800, 480, true, batch);
			inputResponse.registerRenderTree(renderTree);
			Gdx.input.setInputProcessor(new GestureDetector(inputResponse));

			Score.getInstance().reset();
			// Spawn les objets pour le level 1
			// scene = new Scene(deviceWidth,deviceHeight,renderTree);
			scene = new Scene(800, 480, renderTree);
			inputResponse.registerScene(scene);
			// gameOverBG = new LevelBG("data/level1v2_1024.png");
			renderTree.getCurrentStage().act(deltaTime);
			renderTree.getCurrentStage().addActor(gameLevelBG);
			renderTree.getCurrentStage().removeActor(gameOverBG);
			gameState = GameState.PLAYING;

			victoryMusic.stop();
			gameOverMusic.stop();
			newGameSFX.play();
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					MyGdxGame.playingMusic.play();
				}
			}, (long) (TIME_OF_NEW_GAME_SFX * 1000));
			break;
		case VICTORY:
			hud.draw(0);
			renderTree.draw();
			break;
		default:
			gameState = GameState.PLAYING;
		}

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		// mPlay
	}

	@Override
	public void resume() {
	}

}
