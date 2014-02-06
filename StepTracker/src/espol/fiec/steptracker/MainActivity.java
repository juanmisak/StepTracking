package espol.fiec.steptracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends Activity {
	 NumberPicker nP;
	 String[] min = new String[10];
	 Button btRecorridos;
	 	 
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
        
        btRecorridos = (Button)findViewById(R.id.btRecorridos);
        btRecorridos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            Intent i = new Intent(MainActivity.this, VtnRecorridos.class);
            startActivity(i);
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
