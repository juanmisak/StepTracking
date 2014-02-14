package espol.fiec.steptracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DialogoPersonalizado extends DialogFragment {	
	Button btVerMapa;
   /* @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
 
    builder.setView(inflater.inflate(R.layout.dialog_recorrido, null));
    
<<<<<<< HEAD
   
    btVerMapa.setOnClickListener(new View.OnClickListener() {
        public void onClick(View arg0) {
        //Intent i = new Intent(VtnRecorridos.this, ActiviyMap.class);
        //startActivity(i);
        } 
     }); 
=======
 
>>>>>>> bdc37570d54ad4ee50dbebd785529bdffff80fa4
    return builder.create();
    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // R.layout.my_layout - that's the layout where your textview is placed
        View view = inflater.inflate(R.layout.dialog_recorrido, container, false);
         btVerMapa = (Button) view.findViewById(R.id.btMapa);         
         btVerMapa.setOnClickListener(new View.OnClickListener() {
             public void onClick(View arg0) {
               Intent i = new Intent(getActivity().getApplicationContext(),ActiviyMap.class);
               startActivity(i);
             } 
          });
        return view;
    }
    
    
}