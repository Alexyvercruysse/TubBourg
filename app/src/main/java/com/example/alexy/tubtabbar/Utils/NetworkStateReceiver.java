package com.example.alexy.tubtabbar.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.Services.TubApi;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by V on 30/01/2017.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkStateReceiver";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getExtras() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

            if (ni != null && ni.isConnectedOrConnecting()) {
                Log.i(TAG, "Network " + ni.getTypeName() + " connected");
                            TubApi api = TubApi.retrofit.create(TubApi.class);

                            Call<Map<String, Stop>> callStops = api.listStops();
                            callStops.enqueue(new Callback<Map<String, Stop>>() {
                                @Override
                                public void onResponse(Call<Map<String, Stop>> call, Response<Map<String, Stop>> response) {
                                    for(Stop stop : response.body().values()){
                                        stop.save();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Map<String, Stop>> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });


                            Call<Map<String, Hour>> callHours = api.listHours();
                            callHours.enqueue(new Callback<Map<String, Hour>>() {
                                @Override
                                public void onResponse(Call<Map<String, Hour>> call, Response<Map<String, Hour>> response) {
                                    for(Hour hour : response.body().values()){
                                        hour.save();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Map<String, Hour>> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });

                            Call<Map<String, Line>> callLines = api.listLines();
                            callLines.enqueue(new Callback<Map<String, Line>>() {
                                @Override
                                public void onResponse(Call<Map<String, Line>> call, Response<Map<String, Line>> response) {
                                    for(Line line : response.body().values()){
                                        line.save();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Map<String, Line>> call, Throwable t) {
                                    t.printStackTrace();
                                }
                            });


                Log.i(TAG, "BDD " + ni.getTypeName() + " updated");
            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                Log.d(TAG, "There's no network connectivity");
            }
        }
    }
}
