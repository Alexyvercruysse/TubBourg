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
import com.google.maps.android.kml.KmlLayer;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class selectStops extends AppCompatActivity {
    MapView mapView;
    String arret;
    private StopRepository stopRepository;
    private int idLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stops);
        idLine = getIntent().getIntExtra("idLine",0);
        mapView = (MapView) findViewById(R.id.mapView2);
        mapView.onCreate(savedInstanceState);
        stopRepository = new StopRepositoryImpl();

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {

                if(idLine != 0) {
                    KmlLayer layer = null;
                    try {
                        layer = new KmlLayer(map, R.raw.class.getField("line"+idLine).getInt(0),getApplicationContext());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        layer.addLayerToMap();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    for (Stop stop : stopRepository.listStopByIdLine(idLine)) {
                        map.addMarker(Utilities.addStopToMarker(stop));
                    }
                }
                else {
                    finish();
                }
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(stopRepository.listStops().get(0).getLatitude(),stopRepository.listStops().get(0).getLongitude()),13));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        arret = marker.getTitle();
                        return false;
                    }
                });
                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        finish();
                    }
                });
            }
        });
        mapView.onResume();
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


