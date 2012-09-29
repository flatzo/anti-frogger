package ca.polymtl.ourscureuil.activities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ca.polymtl.ourscureuil.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
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
		    adapter.addItem("Truck", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 2*48, 0, 48, 48);
		    adapter.addItem("Lotus rose", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 3*48, 0, 48, 48);
		    adapter.addItem("Barils", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 0, 48, 48, 48);
		    adapter.addItem("Bike", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 48, 48, 48, 48);
		    adapter.addItem("Nascar", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 48*2, 48, 2*48, 48);
		    adapter.addItem("Truck orange", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 0, 2*48, 48, 48);
		    adapter.addItem("Mini van", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 48, 2*48, 48, 48);
		    adapter.addItem("Police", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 2*48, 2*48, 48, 48);
		    adapter.addItem("Mini van mauve", projectileBmp);
		    projectileBmp = Bitmap.createBitmap(bMapScaled, 3*48, 2*48, 48, 48);
		    adapter.addItem("Lotus verte", projectileBmp);
		    
		    
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
		ProjectileItem(String description, Bitmap image) {
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

		public void addItem(String description, Bitmap image) {
			data.add(new ProjectileItem(description,image));
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
				TextView textView = (TextView) rowView.findViewById(R.id.projectile_adapter_description);
				ImageView imageView = (ImageView) rowView.findViewById(R.id.projectile_adapter_sprite);
				ProjectileItem item = data.get(position);
				textView.setText(item.description);
				imageView.setImageBitmap(item.image);
		 
				return rowView;
		}
	}
}
