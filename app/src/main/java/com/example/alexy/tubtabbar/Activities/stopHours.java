package com.example.alexy.tubtabbar.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Utils.Utilities;

import java.util.ArrayList;
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

        // Ouverture du repo pour les horraires
        //HourRepositoryImpl hourRepository = new HourRepositoryImpl(this);
      //  hourRepository.open();

        // Récupération des horraires en fonction de la ligne, +1 pour le sens inverse
      //  List<String> horraireAllez = hourRepository.getHoursByidLineAndidStop(this,getIntent().getIntExtra("idLine",999),getIntent().getIntExtra("idStop",999));
      //  List<String> horraireRetour = hourRepository.getHoursByidLineAndidStop(this,getIntent().getIntExtra("idLine",999)+1,getIntent().getIntExtra("idStop",999));
      //  tvDirection1.setText("Direction : "+getIntent().getStringExtra("premierStop")+"\nProchain passage : "+ Utilities.getNextPassageFromNow(horraireAllez)+"\n");
      //  tvDirection2.setText("Direction : "+getIntent().getStringExtra("dernierStop")+"\nProchain passage : "+Utilities.getNextPassageFromNow(horraireRetour)+"\n");
     //   tvArret.setText("Arrêt : "+getIntent().getStringExtra("nomArret"));


        // Création des arrayAdapter, par défaut "pas de résultat"
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>(){{add("Pas de résultat");}} );
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>(){{add("Pas de résultat");}} );

      //  if (!horraireAllez.isEmpty()){
      //      // Remplir arrayAdapter avec la liste d'horraire
      //      arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, horraireAllez);
       // }
       // if (!horraireRetour.isEmpty()) {
       //     arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, horraireRetour);
      //  }

      //  direction1.setAdapter(arrayAdapter);
      //  direction2.setAdapter(arrayAdapter2);
    }
}