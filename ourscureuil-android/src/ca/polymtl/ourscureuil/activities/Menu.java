package ca.polymtl.ourscureuil.activities;

import ca.polymtl.ourscureuil.MainActivity;
import ca.polymtl.ourscureuil.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity {
	
    private OnClickListener clickToPlay = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent().setClass(getApplicationContext(),
                    MainActivity.class);
			startActivity(intent);
		}
    };
    
    private OnClickListener clickForInstructions = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent().setClass(getApplicationContext(),
                    Instructions.class);
			startActivity(intent);
		}
    };
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.menu);
        
        Button btnPlay = (Button) this.findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(clickToPlay);
        
        Button btnInstructions = (Button) this.findViewById(R.id.btn_instructions);
        btnInstructions.setOnClickListener(clickForInstructions);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}