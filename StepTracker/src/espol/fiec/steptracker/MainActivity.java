package espol.fiec.steptracker;

import java.util.TooManyListenersException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	NumberPicker nP;
	ToggleButton tg;
	String[] min = new String[10];
	ToggleButton tg2;	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		for (int i=0;i<10;i++){
        	min[i]=Integer.toString(i+1);
        }
        nP = (NumberPicker)findViewById(R.id.nPminutos);
        nP.setMinValue(1);
        nP.setMaxValue(10);
        nP.setWrapSelectorWheel(true);
        nP.setDisplayedValues(min);
        nP.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);        
        tg2 = (ToggleButton)findViewById(R.id.toggleButton2);
        tg2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            Intent i = new Intent(MainActivity.this, VtnRecorridos.class);
            if(tg2.isChecked())
            	startActivity(i);
            } 
         });
        tg = (ToggleButton)findViewById(R.id.toggleButton1);
        tg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            	if(tg.isChecked()){
                  	Toast toast = Toast.makeText(getApplicationContext(), "ACTIVADO", Toast.LENGTH_SHORT);
            		toast.show();
            		tg2.setEnabled(false);
            		tg2.setChecked(false);
            		
            	}
            	else
        		{
            		Toast toast = Toast.makeText(getApplicationContext(), "DESACTIVADO", Toast.LENGTH_SHORT);
            		toast.show();
            		tg2.setEnabled(true);
            		tg2.setChecked(true);
        		}
                
            } 
         });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
