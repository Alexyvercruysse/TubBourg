package com.example.alexy.tubtabbar.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.alexy.tubtabbar.Activities.selectStops;
import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Repositories.LineRepository;
import com.example.alexy.tubtabbar.Repositories.LineRepositoryImpl;

import static android.app.Activity.RESULT_OK;


public class TravelFragment extends Fragment {
    static final int STOP_REQUEST = 0;
    static final int STOP_REQUEST_END = 1;
    private LineRepository lineRepository;
    Button buttonStart, buttonEnd, buttonRun;
    private View view;
    private int idLine;
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
        lineRepository = new LineRepositoryImpl();
        view = inflater.inflate( R.layout.fragment_travel, container, false );

        buttonStart = (Button) view.findViewById(R.id.buttonStopStart);
        buttonEnd = (Button) view.findViewById(R.id.buttonStopEnd);
        buttonRun = (Button) view.findViewById(R.id.buttonRun);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialog();
            }
        });

        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idLine == 0){
                    Toast.makeText(getActivity(),"Selectionner le départ d'abord",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent myIntent = new Intent(getActivity(), selectStops.class);
                    myIntent.putExtra("idLine", idLine);
                    myIntent.putExtra("EndStops", true);
                    startActivityForResult(myIntent, STOP_REQUEST_END);
                }
            }
        });

        return view;
    }

    private void launchDialog(){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
        //builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Ligne : ");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1);
        for (Line line : lineRepository.listLines()){
            arrayAdapter.add(line.getName());

        }

        builderSingle.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    
                   String strName = arrayAdapter.getItem(which);
                   idLine = lineRepository.getLineByName(strName).getId();
                   Intent myIntent = new Intent(getActivity(), selectStops.class);
                   myIntent.putExtra("idLine", idLine);
                   startActivityForResult(myIntent, STOP_REQUEST);

            }
        });
        builderSingle.show();
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
