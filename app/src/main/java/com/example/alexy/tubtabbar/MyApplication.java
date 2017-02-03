package com.example.alexy.tubtabbar;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import com.example.alexy.tubtabbar.Repositories.MySQLite;
import com.example.alexy.tubtabbar.Utils.NetworkStateReceiver;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by iem on 30/11/2016.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        NetworkStateReceiver receiver = new NetworkStateReceiver();
        registerReceiver(receiver, filter);
    }
}
