package espol.fiec.steptracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
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


		
		calendario = (CalendarView)findViewById(R.id.calendarView);

		
		
		calendario.setOnDateChangeListener(new OnDateChangeListener(){	

			public void onSelectedDayChange(CalendarView view,int year, int month, int dayOfMonth) {
				final Dialog dialog = new Dialog(VtnRecorridos.this);
				dialog.setContentView(R.layout.dialog_recorrido);
				
				dialog.show();
				
				
				
				Button btMapa = (Button) dialog.findViewById(R.id.btMapa);
				btMapa.setOnClickListener(new View.OnClickListener() {

					// Closing SecondScreen Activity
					public void onClick(View arg0) {
						
						Intent i = new Intent(VtnRecorridos.this, MapClass.class);
						startActivity(i);
						
					}
				});
				
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
