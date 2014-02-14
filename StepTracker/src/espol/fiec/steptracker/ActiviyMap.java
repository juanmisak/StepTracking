package espol.fiec.steptracker;

import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ActiviyMap extends android.support.v4.app.FragmentActivity {

	ArrayList<LatLng> markerPoints;
	private GoogleMap mMap;
	private OnLocationChangedListener mListener;
	 private boolean haveLocation = false;
	 Button btAtras;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activiy_map);
		
		markerPoints = new ArrayList<LatLng>();

		btAtras = (Button)findViewById(R.id.btAtras);
		btAtras.setOnClickListener(new View.OnClickListener() {			 
            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });
		
        initializeMap();

		
	}
	
	private void initializeMap() {
		  // Confirmamos que no se ha inicializado el mapa todavía
		  if (mMap == null) 
		  {
		   // obtenemos el mapa de la vista
		   mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	
		   // Inicializamos el mapa...
		   if (mMap != null) 
		   {
		    setUpMap();     
		   }
		  }
	}

	private void setUpMap() 
	 {
	  // Nos localiza...
	  mMap.setMyLocationEnabled(true);
	  // Quitamos el botón de "mi posición"
	 // mMap.getUiSettings().setMyLocationButtonEnabled(false);
	  // Pinta una marca en un punto 
	  //addMarkOnMap();
	  //centerMapOn();
	  
	// Setting onclick event listener for the map
	  mMap.setOnMapClickListener(new OnMapClickListener() {
		
		@Override
		public void onMapClick(LatLng point) {
			
			// Already two locations				
			if(markerPoints.size()>1){
				markerPoints.clear();
				mMap.clear();					
			}
			
			// Adding new item to the ArrayList
			markerPoints.add(point);				
			
			// Creating MarkerOptions
			MarkerOptions options = new MarkerOptions();
			
			// Setting the position of the marker
			options.position(point);
			
			/** 
			 * For the start location, the color of marker is GREEN and
			 * for the end location, the color of marker is RED.
			 */
			if(markerPoints.size()==1){
				options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			}else if(markerPoints.size()==2){
				options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
			}
						
			
			// Add new marker to the Google Map Android API V2
			mMap.addMarker(options);
			
			// Checks, whether start and end locations are captured
			if(markerPoints.size() >= 2){					
				LatLng origin = markerPoints.get(0);
				LatLng dest = markerPoints.get(1);
				
			}
			
		  }
	  	});
	  
	 }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activiy_map, menu);
		return true;
	}

}
