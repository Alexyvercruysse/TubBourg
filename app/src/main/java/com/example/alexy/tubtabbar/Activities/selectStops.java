package com.example.alexy.tubtabbar.Activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Utils.Utilities;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.kml.KmlLayer;

import java.util.ArrayList;
import java.util.List;

public class selectStops extends AppCompatActivity {
    MapView mapView;
    Button btn8;
    String arret;
    Stop gare;
    Stop vicaire;
    Stop chariteUniversitaire;
    Line ligne5;
    List<Stop> ls;
    KmlLayer layer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stops);
        // Création d'un jeu d'essai en brut
        gare = new Stop();
        vicaire = new Stop();
        chariteUniversitaire = new Stop();
        ligne5 = new Line();
        ligne5.setId(5);
        ligne5.setName("Ligne 5");
        ligne5.setDescription("La description de la ligne 5");
       // gare.setId(1);gare.setName("Gare");gare.setDescription("Gare des bus");gare.setGpsCoord(46.207337, 5.227646);
      //  vicaire.setId(2);vicaire.setName("Vicaire");vicaire.setDescription("Arrêt Vicaire");vicaire.setGpsCoord(46.207929, 5.224553);
       // chariteUniversitaire.setId(3);chariteUniversitaire.setName("Charité Universitaire");chariteUniversitaire.setDescription("Arrêt Charité Universitaire");chariteUniversitaire.setGpsCoord(46.207634, 5.219873);
        ls = new ArrayList<>();
        ls.add(gare);
        ls.add(vicaire);
        ls.add(chariteUniversitaire);

        mapView = (MapView) findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {

            //    map.moveCamera(CameraUpdateFactory.newLatLngZoom(gare.getGpsCoord(), 13));
                map.addMarker(Utilities.addStopToMarker(vicaire));
                map.addMarker(Utilities.addStopToMarker(gare));
                map.addMarker(Utilities.addStopToMarker(chariteUniversitaire));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        arret = marker.getTitle();
                        btn8.setText("Arrêt : "+arret);
                        return false;
                    }
                });

            }
        });
        mapView.onResume();
        btn8 = (Button) findViewById(R.id.button8);


    }

    public void arretIsSelect(View v) {

        if (arret != null) {
            finish();
        } else {
            Toast.makeText(this, "Pas d'arrêt sélectionné", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finish(){
        if (getIntent().getBooleanExtra("EndStops",false))
        {
            Intent intentB = new Intent();
            intentB.putExtra("result", arret);
            setResult(RESULT_OK, intentB);
        }
        else {
            Intent intentB = new Intent();
            intentB.putExtra("result", arret);
            setResult(RESULT_OK, intentB);
        }
        super.finish();
    }
}


