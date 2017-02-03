package com.example.alexy.tubtabbar.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.alexy.tubtabbar.Repositories.HourRepository;
import com.example.alexy.tubtabbar.Repositories.HourRepositoryImpl;
import com.example.alexy.tubtabbar.Repositories.LineRepository;
import com.example.alexy.tubtabbar.Repositories.LineRepositoryImpl;
import com.example.alexy.tubtabbar.Repositories.StopRepository;
import com.example.alexy.tubtabbar.Repositories.StopRepositoryImpl;

/**
 * Created by V on 30/01/2017.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    private static final String TAG = "NetworkStateReceiver";

    @Override
    public void onReceive(final Context context, final Intent intent) {

        Log.d(TAG, "Network connectivity change");

        if (intent.getExtras() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

            if (ni != null && ni.isConnectedOrConnecting()) {
                Log.i(TAG, "Network " + ni.getTypeName() + " connected");
                //TODO UPDATE BDD
                HourRepository hourRepository = new HourRepositoryImpl();
                StopRepository stopRepository = new StopRepositoryImpl();
                LineRepository lineRepository = new LineRepositoryImpl();

                Log.i(TAG, "BDD " + ni.getTypeName() + " updated");
            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                Log.d(TAG, "There's no network connectivity");
            }
        }
    }
}
