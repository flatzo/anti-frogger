package ca.polymtl.ourscureuil.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import ca.polymtl.ourscureuil.R;
import ca.polymtl.ourscureuil.Score;

public class Options extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.options);
        
      EditText time = (EditText)findViewById(R.id.editText_time);
      time.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				System.out.println("bob");
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				System.out.println("bob");
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				System.out.println("bob");
				// TODO Auto-generated method stub
				String value = s.toString();
				if(value.matches("[0-9]:[0-9][0-9]")) {
					String[] values = value.split(":");
					Score.getInstance().setTime(
							60*Integer.valueOf(values[0]) + Integer.valueOf(values[1]));
				}
			}

      }); 
      
      
      
      
      
      
      SeekBar bar = (SeekBar)findViewById(R.id.seekBar_life);
      final TextView lifeCount = (TextView)findViewById(R.id.text_lifeCount);
      
      bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			Score.getInstance().setLife(progress);
			String value = String.valueOf(progress);
			lifeCount.setText(value);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
    	  
      });
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