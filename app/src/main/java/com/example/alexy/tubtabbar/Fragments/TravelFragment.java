package com.example.alexy.tubtabbar.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.test.suitebuilder.TestMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alexy.tubtabbar.Activities.selectStops;
import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Repositories.HourRepository;
import com.example.alexy.tubtabbar.Repositories.HourRepositoryImpl;
import com.example.alexy.tubtabbar.Repositories.LineRepository;
import com.example.alexy.tubtabbar.Repositories.LineRepositoryImpl;
import com.example.alexy.tubtabbar.Repositories.StopRepository;
import com.example.alexy.tubtabbar.Repositories.StopRepositoryImpl;
import com.example.alexy.tubtabbar.Utils.Utilities;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class TravelFragment extends Fragment {
    static final int STOP_REQUEST = 0;
    static final int STOP_REQUEST_END = 1;
    private LineRepository lineRepository;
    private Button buttonStart, buttonEnd, buttonRun;
    private TextView result;
    private TimePicker timePicker;
    private View view;
    private int idLine;
    private Stop startStop, endStop;
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
        result = (TextView) view.findViewById(R.id.result);
        timePicker = (TimePicker) view.findViewById(R.id.timePickerTravel);

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
                    Toast.makeText(getActivity(),"Veuillez sélectionner l'arrêt de départ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent myIntent = new Intent(getActivity(), selectStops.class);
                    myIntent.putExtra("idLine", idLine);
                    myIntent.putExtra("EndStops", true);
                    startActivityForResult(myIntent, STOP_REQUEST_END);
                }
            }
        });

        buttonRun.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(startStop != null && endStop != null){
                    HourRepository hourRepository = new HourRepositoryImpl();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");


                    List<Hour> startHourList = hourRepository.listHoursByNameStop(startStop.getName());
                    String date = timePicker.getHour() + ":" + timePicker.getMinute();
                    Date choosedDate = new Date();
                    try {
                        choosedDate = simpleDateFormat.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String firstPassage = Utilities.getNextPassageFromDate(startHourList, choosedDate);
                    Date firstDate = new Date();
                    try {
                        firstDate = simpleDateFormat.parse(firstPassage);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    List<Hour> endHourList = hourRepository.listHoursByNameStop(endStop.getName());
                    String secondPassage = Utilities.getNextPassageFromDate(endHourList, firstDate);
                    result.setText(String.format("arrêt %s - prochain passage : %s \n arrêt %s - prochain passage : %s",startStop.getName(), firstPassage, endStop.getName(), secondPassage));
                } else {
                    Toast.makeText(getActivity(),"Veuillez sélectionner les arrêts",Toast.LENGTH_SHORT).show();
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
                    if (idLine != 21){
                        dialog.dismiss();
                        idLine = 0;
                        Toast.makeText(getActivity(),"Cette fonctionnalitée n'est pas encore disponible pour cette ligne",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent myIntent = new Intent(getActivity(), selectStops.class);
                        myIntent.putExtra("idLine", idLine);
                        startActivityForResult(myIntent, STOP_REQUEST);
                    }
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
        StopRepository stopRepository = new StopRepositoryImpl();
        if (requestCode == STOP_REQUEST && resultCode == RESULT_OK)
        {
            buttonStart.setText("Départ : " + data.getStringExtra("result"));
            startStop = stopRepository.getStopByName(data.getStringExtra("result"));
        }
        if (requestCode == STOP_REQUEST_END && resultCode == RESULT_OK)
        {
            buttonEnd.setText("Arrivé : " + data.getStringExtra("result"));
            endStop = stopRepository.getStopByName(data.getStringExtra("result"));
        }
    }
}
