package ca.polymtl.ourscureuil.activities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ca.polymtl.ourscureuil.R;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Projectiles extends ListActivity  {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.projectiles);
		
		ProjectileAdapter adapter = new ProjectileAdapter();
		//Bitmap bmp = BitmapFactory.decodeFile("assets/data/greenCar.png");
		//Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Bitmap spriteSheet;
		try {
			spriteSheet = getBitmapFromAsset("data/vehicles_512.png");
		    Bitmap bMapScaled = Bitmap.createScaledBitmap(spriteSheet, 512, 512, true);

		    Bitmap projectileBmp;
		    
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 0, 0, 2*48, 48);
		    adapter.addItem("Truck","Slow, won't stop for any obstacle.", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 2*48, 0, 48, 48);
		    adapter.addItem("Lotus rose","Fast, will be deviated from any interference.", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 3*48, 0, 48, 48);
		    adapter.addItem("Barrels","High explosive, touch it and enjoy.", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 0, 48, 48, 48);
		    adapter.addItem("Bike","Really just an handicap", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 48, 48, 48, 48);
		    adapter.addItem("Nascar","Drift is in the air.", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 48*2, 48, 2*48, 48);
		    adapter.addItem("Orange truck","Slow, won't stop for any obstacle. If thrown too fast, will overturn.", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 0, 2*48, 48, 48);
		    adapter.addItem("Mini van","Kids are in that truck and they like frogs ! Don't be sadistic with that one.", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 48, 2*48, 48, 48);
		    adapter.addItem("Police","Getting close to a frog, cop will aim and shoot.", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 2*48, 2*48, 48, 48);
		    adapter.addItem("Mini van mauve","....", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 3*48, 2*48, 48, 48);
		    adapter.addItem("Lotus verte","Fast, will be deviated from any interference. Mowing the gras will bring it back on the tarmac.", projectileBmp);
		    
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		setListAdapter(adapter);
	}
	
	private Bitmap getBitmapFromAsset(String strName) throws IOException
    {
        AssetManager assetManager = getAssets();

        InputStream istr = assetManager.open(strName);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        return bitmap;
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
	
	public class ProjectileItem {
		String description;
		Bitmap image;
		String name;
		ProjectileItem(String title, String description, Bitmap image) {
			this.name = title;
			this.description = description;
			this.image = image;
		}
	}

	private class ProjectileAdapter extends BaseAdapter {

		private ArrayList<ProjectileItem> data = new ArrayList<ProjectileItem>();
		
		private LayoutInflater inflater;

		public ProjectileAdapter() {
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void addItem(String title, String description, Bitmap image) {
			data.add(new ProjectileItem(title,description,image));
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public ProjectileItem getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		 
				View rowView = inflater.inflate(R.layout.projectile_adapter, parent, false);
				TextView description = (TextView) rowView.findViewById(R.id.projectile_adapter_description);
				TextView name = (TextView) rowView.findViewById(R.id.projectile_adapter_name);
				ImageView imageView = (ImageView) rowView.findViewById(R.id.projectile_adapter_sprite);
				ProjectileItem item = data.get(position);
				name.setText(item.name);
				description.setText(item.description);
				imageView.setImageBitmap(item.image);
		 
				return rowView;
		}
	}
}
