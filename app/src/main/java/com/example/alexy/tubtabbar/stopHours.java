package com.example.alexy.tubtabbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexy.tubtabbar.Repository.HourRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class stopHours extends AppCompatActivity {

    ListView direction1,direction2;
    TextView tvArret,tvDirection1,tvDirection2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_hours);

        direction1 = (ListView) findViewById(R.id.directionList1);
        direction2 = (ListView) findViewById(R.id.directionList2);
        tvArret = (TextView) findViewById(R.id.tvArret);
        tvDirection1 = (TextView) findViewById(R.id.tvDirection1);
        tvDirection2 = (TextView) findViewById(R.id.tvDirection2);
        HourRepositoryImpl hourRepository = new HourRepositoryImpl(this);
        hourRepository.open();
        List<String> horraireAllez = hourRepository.getHoursByidLineAndidStop(this,getIntent().getIntExtra("idLine",999),getIntent().getIntExtra("idStop",999));
        List<String> horraireRetour = hourRepository.getHoursByidLineAndidStop(this,getIntent().getIntExtra("idLine",999)+1,getIntent().getIntExtra("idStop",999));
        tvDirection1.setText("Direction : "+getIntent().getStringExtra("premierStop")+"\nProchain horraire : "+Utilities.getNextPassageFromNow(horraireAllez));
        tvDirection2.setText("Direction : "+getIntent().getStringExtra("dernierStop")+"\nProchain horraire : "+Utilities.getNextPassageFromNow(horraireRetour));
        tvArret.setText("Arrêt : "+getIntent().getStringExtra("nomArret"));
        Log.d("tag",""+Utilities.getNextPassageFromNow(horraireAllez));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>(){{add("Pas de résultat");}} );
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>(){{add("Pas de résultat");}} );
        if (!horraireAllez.isEmpty()){
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, horraireAllez);
        }
        if (!horraireRetour.isEmpty()) {
            arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, horraireRetour);
        }

        direction1.setAdapter(arrayAdapter);
        direction2.setAdapter(arrayAdapter2);
    }
}
