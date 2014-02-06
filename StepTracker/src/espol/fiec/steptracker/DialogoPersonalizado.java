package espol.fiec.steptracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class DialogoPersonalizado extends DialogFragment {		
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
 
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
 
    builder.setView(inflater.inflate(R.layout.dialog_recorrido, null));
    
 
    return builder.create();
    }
    
    
}