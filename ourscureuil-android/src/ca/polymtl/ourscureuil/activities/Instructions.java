package ca.polymtl.ourscureuil.activities;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import ca.polymtl.ourscureuil.R;

public class Instructions extends TabActivity  {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.instruction);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
        
        intent = new Intent().setClass(this, GeneralInstructions.class);
        spec = tabHost.newTabSpec("general").setIndicator("General instructions").setContent(intent);
        tabHost.addTab(spec);
        
        
        intent = new Intent().setClass(this, Projectiles.class);
        spec = tabHost.newTabSpec("projectiles").setIndicator("Projectiles types").setContent(intent);
        tabHost.addTab(spec);

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
