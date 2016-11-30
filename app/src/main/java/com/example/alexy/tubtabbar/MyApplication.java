package com.example.alexy.tubtabbar;

import android.app.Application;

import com.example.alexy.tubtabbar.Repositories.MySQLite;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by iem on 30/11/2016.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //We create our db by a file
        MySQLite db = MySQLite.getInstance(getApplicationContext());
        db.getWritableDatabase();

        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        // add for verbose logging
        // FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }


}
