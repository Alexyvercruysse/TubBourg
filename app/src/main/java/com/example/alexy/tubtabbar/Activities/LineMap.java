package com.example.alexy.tubtabbar.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class LineMap extends AppCompatActivity {
    MapView mapView;
    KmlLayer layer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_map);

        final String nameLine = getIntent().getStringExtra("nameLine"); // Si pas de valeurs set a 999 pour return null

        // Connexion to repositories
        final StopRepository stopRepository = new StopRepositoryImpl();
        final LineRepository lineRepository = new LineRepositoryImpl();

        final Line line = lineRepository.getLineByName(nameLine);

        final List<Stop> stops = stopRepository.listStopByIdLine(line.getId()); // Si pas de valeurs set a 999 pour return null

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                try {
                    layer = new KmlLayer(map, R.raw.class.getField("line"+(String.valueOf(line.getId()))).getInt(null), getApplicationContext());
                    layer.addLayerToMap();
                    for (MarkerOptions markerOptions : Utilities.addStopToMarker(stops)) {
                        map.addMarker(markerOptions);
                    }

                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {

                            //Envoie sur l'activité des horraires avec toutes les informations
                            Intent intent = new Intent(getApplication(), stopHours.class);
                            intent.putExtra("nameStop", marker.getTitle());
                            intent.putExtra("idLine", line.getId());
                            intent.putExtra("idStop", stopRepository.getStopByName(marker.getTitle()).getId());
                            startActivity(intent);
                        }
                    });

                    //Move Cam on the center of Bourg-en-Bresse
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.207337, 5.227646), 13));

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        });
        mapView.onResume();
    }
}
