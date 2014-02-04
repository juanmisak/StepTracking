package espol.fiec.steptracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;
public class VtnRecorridos extends Activity {

	Button btAtras, btMapa;
	CalendarView calendario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vtn_recorridos);
		
		btAtras = (Button)findViewById(R.id.btAtras);
		btAtras.setOnClickListener(new View.OnClickListener() {			 
            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });
		
		btMapa = (Button)findViewById(R.id.btMapa);
		btMapa.setOnClickListener(new View.OnClickListener() {			 
            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                
                setContentView(R.layout.activity_map);   
            }
        });
		
		
		calendario = (CalendarView)findViewById(R.id.calendarView);
		calendario.setOnDateChangeListener(new OnDateChangeListener(){		 
			public void onSelectedDayChange(CalendarView view,int year, int month, int dayOfMonth) {
				DialogoPersonalizado d = new DialogoPersonalizado();
				FragmentManager f = getFragmentManager();
				d.show(f,"recorrido");
				
				Toast.makeText(getApplicationContext(),"Aqui saldr� la ventana",Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(),dayOfMonth +"/"+month+"/"+ year,Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}

	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vtn_recorridos, menu);
		return true;
	}

}
