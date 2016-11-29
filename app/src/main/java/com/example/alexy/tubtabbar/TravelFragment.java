package com.example.alexy.tubtabbar;

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

import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class TravelFragment extends Fragment {
    static final int STOP_REQUEST = 0;
    static final int STOP_REQUEST_END = 1;
    Button btn5,btn6,btn4;
    private View retVal = null;

    public TravelFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        retVal = inflater.inflate( R.layout.fragment_trajet, container, false );

        btn5 = (Button) retVal.findViewById(R.id.button5);
        btn6 = (Button) retVal.findViewById(R.id.button6);
        btn4 = (Button) retVal.findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        btn4.setText("Arrivé a : "+i+"H et "+i1+"Min");
                    }
                },new Date().getHours(),new Date().getMinutes(),true);
                timePickerDialog.show();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), selectStops.class);
                startActivityForResult(myIntent, STOP_REQUEST);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            btn5.setText("Départ : "+data.getStringExtra("result"));
        }
        if (requestCode == STOP_REQUEST_END && resultCode == RESULT_OK)
        {
            btn6.setText("Arrivé : "+data.getStringExtra("result"));
        }
    }


}
