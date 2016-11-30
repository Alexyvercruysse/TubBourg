package com.example.alexy.tubtabbar.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Repositories.HourRepository;
import com.example.alexy.tubtabbar.Repositories.HourRepositoryImpl;
import com.example.alexy.tubtabbar.Utils.Utilities;

import java.util.ArrayList;
import java.util.List;

public class stopHours extends AppCompatActivity {

    ListView direction1,direction2;
    TextView tvStop,tvDirection1,tvDirection2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_hours);

        direction1 = (ListView) findViewById(R.id.directionList1);
        direction2 = (ListView) findViewById(R.id.directionList2);
        tvStop = (TextView) findViewById(R.id.tvArret);
        tvDirection1 = (TextView) findViewById(R.id.tvDirection1);
        tvDirection2 = (TextView) findViewById(R.id.tvDirection2);

        // Open Repositories
        HourRepository hourRepository = new HourRepositoryImpl();

        //FIXME 999
        int idLine = getIntent().getIntExtra("idLine",999);
        int idStop = getIntent().getIntExtra("idStop",999);

        List<Integer> directions = hourRepository.listDirectionsByIdLine(idLine);

        Log.d("Yolo",directions.size() + "");

        List<Hour> firstDirection = new ArrayList<>();
        List<Hour> secondDirection = new ArrayList<>();

        if(directions.size() == 2){
            firstDirection = hourRepository.listHoursByIdLineAndIdStopAndIdDirection(idLine, idStop, directions.get(0));
            secondDirection = hourRepository.listHoursByIdLineAndIdStopAndIdDirection(idLine, idStop, directions.get(1));
        }


        tvDirection1.setText("Direction : "+getIntent().getStringExtra("firstStop")+"\nProchain passage : "+ Utilities.getNextPassageFromNow(firstDirection)+"\n");
        tvDirection2.setText("Direction : "+getIntent().getStringExtra("lastStop")+"\nProchain passage : "+Utilities.getNextPassageFromNow(secondDirection)+"\n");
        tvStop.setText("Arrêt : "+getIntent().getStringExtra("nameStop"));


        //Creation arrayAdapters, défault : "pas de résultat"
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>(){{add("Pas de résultat");}} );
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>(){{add("Pas de résultat");}} );

        if (!firstDirection.isEmpty()){
            List<String> hours = new ArrayList<>();
            for(Hour hour : firstDirection){
                hours.add(hour.getHour());
            }
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hours);
        }
        if (!secondDirection.isEmpty()) {
            List<String> hours = new ArrayList<>();
            for(Hour hour : secondDirection){
                hours.add(hour.getHour());
            }
            arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hours);
        }

        direction1.setAdapter(arrayAdapter);
        direction2.setAdapter(arrayAdapter2);
    }
}
