package com.example.alexy.tubtabbar.Fragments;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.alexy.tubtabbar.Activities.selectStops;
import com.example.alexy.tubtabbar.R;

import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class TravelFragment extends Fragment {
    static final int STOP_REQUEST = 0;
    static final int STOP_REQUEST_END = 1;
    Button buttonStart, buttonEnd, buttonSelectHour;
    private View retVal = null;

    public TravelFragment() {
    }

    public static TravelFragment newInstance(){
        TravelFragment travelFragment = new TravelFragment();
        return travelFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        retVal = inflater.inflate( R.layout.fragment_travel, container, false );

        buttonStart = (Button) retVal.findViewById(R.id.button5); // Lieu de départ
        buttonEnd = (Button) retVal.findViewById(R.id.button6);
        buttonSelectHour = (Button) retVal.findViewById(R.id.button4);

        // FIXME : Use non-depreciated func
        buttonSelectHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Affiche dialog timePicker
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        buttonSelectHour.setText("Arrivé a : "+i+"H et "+i1+"Min");
                    }
                }, new Date().getHours(),new Date().getMinutes(),true);
                timePickerDialog.show();
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lancer l'activité pour selectionner l'arrêt de départ
                Intent myIntent = new Intent(getActivity(), selectStops.class);
                startActivityForResult(myIntent, STOP_REQUEST);
            }
        });
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lancer l'activité pour selectionner l'arrêt d'arrivée
                Intent myIntent = new Intent(getActivity(), selectStops.class);
                myIntent.putExtra("EndStops",true);
                startActivityForResult(myIntent, STOP_REQUEST_END);
            }
        });
        return retVal;


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == STOP_REQUEST && resultCode == RESULT_OK)
        {
            buttonStart.setText("Départ : "+data.getStringExtra("result"));
        }
        if (requestCode == STOP_REQUEST_END && resultCode == RESULT_OK)
        {
            buttonEnd.setText("Arrivé : "+data.getStringExtra("result"));
        }
    }


}
