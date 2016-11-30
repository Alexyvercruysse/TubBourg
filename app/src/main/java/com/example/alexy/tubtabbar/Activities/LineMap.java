package com.example.alexy.tubtabbar.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.R;
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
        final int numLine = getIntent().getIntExtra("numLigne",999); // Si pas de valeurs set a 999 pour return null

        // Connection au repo pour récupérer les Arrêts
   //     final StopRepositoryImpl stopRepository = new StopRepositoryImpl(this);
     //   stopRepository.open();

        // Connection au repo pour récupérer les Horraires
     //   HourRepositoryImpl hourRepository = new HourRepositoryImpl(this);
      //  hourRepository.open();


      //  final List<Stop> stops = hourRepository.getStopsByLine(this,getIntent().getIntExtra("numLigne",999)); // Si pas de valeurs set a 999 pour return null

        // Création de la mapView
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                try {
                    // Ajout du kml sur la map
                    switch (numLine){
                        case 1:
                            layer = new KmlLayer(map, R.raw.ligne1, getApplicationContext());
                            layer.addLayerToMap();
                            break;
                        case 2:
                            layer = new KmlLayer(map, R.raw.ligne2, getApplicationContext());
                            layer.addLayerToMap();
                            break;
                        case 3:
                            layer = new KmlLayer(map, R.raw.ligne3, getApplicationContext());
                            layer.addLayerToMap();
                            break;
                        case 4:
                            layer = new KmlLayer(map, R.raw.ligne4, getApplicationContext());
                            layer.addLayerToMap();
                            break;
                        case 5:
                            layer = new KmlLayer(map, R.raw.ligne5, getApplicationContext());
                            layer.addLayerToMap();
                            break;
                        case 6:
                            layer = new KmlLayer(map, R.raw.ligne3, getApplicationContext());
                            layer.addLayerToMap();

                            break;
                        case 7:
                            layer = new KmlLayer(map, R.raw.ligne3, getApplicationContext());
                            layer.addLayerToMap();
                            break;
                        case 0:
                            layer = new KmlLayer(map, R.raw.ligne21, getApplicationContext());
                            layer.addLayerToMap();
                        //    for (MarkerOptions markerOptions : Utilities.addStopToMarker(stops)){
                        //        map.addMarker(markerOptions);
                       //     }

                            break;
                    }

                    // Ajout du click sur le nom de l'arrêt
                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {

                            //Envoie sur l'activité des horraires avec toutes les informations
                                    Intent intent = new Intent(getApplication(),stopHours.class);
                                    intent.putExtra("nomArret",marker.getTitle());
                                    intent.putExtra("idLine",numLine);
                           //         intent.putExtra("idStop",stopRepository.getStopByName(marker.getTitle()));
                            //        intent.putExtra("dernierStop",stops.get(0).getName());
                             //       intent.putExtra("premierStop",stops.get(stops.size()-1).getName());
                                    startActivity(intent);
                        }
                    });

                    // Mouvement de la caméra sur Bourg
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.207337, 5.227646), 13));

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        mapView.onResume();

    }
}
