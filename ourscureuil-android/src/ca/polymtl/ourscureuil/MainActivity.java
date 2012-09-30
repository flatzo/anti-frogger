package ca.polymtl.ourscureuil;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        SoundManager.getInstance(); //creates the instance
		SoundManager.initSounds(this);
		
		
		
        initialize(new MyGdxGame(), cfg);
    }
    
}