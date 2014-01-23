package espol.fiec.steptracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.NumberPicker;

public class MainActivity extends Activity {
	 NumberPicker nP;
	 String[] min = new String[10];
	 	 
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
        nP.setWrapSelectorWheel(false);
        nP.setDisplayedValues(min);
        
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
