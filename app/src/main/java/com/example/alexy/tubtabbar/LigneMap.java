package com.example.alexy.tubtabbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.Repository.HourRepositoryImpl;
import com.example.alexy.tubtabbar.Repository.StopRepository;
import com.example.alexy.tubtabbar.Repository.StopRepositoryImpl;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LigneMap extends AppCompatActivity {
    MapView mapView;
    KmlLayer layer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligne_map);
        final int numLine = getIntent().getIntExtra("numLigne",999);
        final StopRepositoryImpl stopRepository = new StopRepositoryImpl(this);
        stopRepository.open();
        HourRepositoryImpl hourRepository = new HourRepositoryImpl(this);
        hourRepository.open();
        final List<Stop> stops = hourRepository.getStopsByLine(this,getIntent().getIntExtra("numLigne",999));
        final int numberStops = stops.size();
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                try {
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
                            for (MarkerOptions markerOptions : Utilities.addStopToMarker(stops)){
                                map.addMarker(markerOptions);
                            }

                            break;
                    }
                    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
//                                    Intent intent = new Intent(getApplication(),stopHours.class);
//                                    intent.putExtra("nomArret",marker.getTitle());
//                                    intent.putExtra("idLine",numLine);
//                                    intent.putExtra("idStop",stopRepository.getStopByName(marker.getTitle()));
//                                    intent.putExtra("dernierStop",stops.get(0).getName());
//                                    intent.putExtra("premierStop",stops.get(stops.size()-1).getName());
//                                    startActivity(intent);


                            return false;
                        }
                    });
                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                                    Intent intent = new Intent(getApplication(),stopHours.class);
                                    intent.putExtra("nomArret",marker.getTitle());
                                    intent.putExtra("idLine",numLine);
                                    intent.putExtra("idStop",stopRepository.getStopByName(marker.getTitle()));
                                    intent.putExtra("dernierStop",stops.get(0).getName());
                                    intent.putExtra("premierStop",stops.get(stops.size()-1).getName());
                                    startActivity(intent);
                        }
                    });

                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.207337, 5.227646), 13));

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                LatLng sydney = new LatLng(-33.867, 151.206);
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//
//                map.addMarker(new MarkerOptions()
//                        .title("Sydney")
//                        .snippet("The most populous city in Australia.")
//                        .position(sydney));

            }
        });
        mapView.onResume();

    }
}
