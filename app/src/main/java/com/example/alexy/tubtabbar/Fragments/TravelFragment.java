package com.example.alexy.tubtabbar.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alexy.tubtabbar.Activities.selectStops;
import com.example.alexy.tubtabbar.R;

import static android.app.Activity.RESULT_OK;


public class TravelFragment extends Fragment {
    static final int STOP_REQUEST = 0;
    static final int STOP_REQUEST_END = 1;

    Button buttonStart, buttonEnd, buttonRun;
    private View view;

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
        view = inflater.inflate( R.layout.fragment_travel, container, false );

        buttonStart = (Button) view.findViewById(R.id.buttonStopStart);
        buttonEnd = (Button) view.findViewById(R.id.buttonStopEnd);
        buttonRun = (Button) view.findViewById(R.id.buttonRun);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), selectStops.class);
                startActivityForResult(myIntent, STOP_REQUEST);
            }
        });

        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), selectStops.class);
                myIntent.putExtra("EndStops",true);
                startActivityForResult(myIntent, STOP_REQUEST_END);
            }
        });

        return view;
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
