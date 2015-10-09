package com.example.administrator.weakrefrence;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by Administrator on 2015/10/8.
 */
public class weakApplication extends Application {

    public static Context mApplicationContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
        Log.e("debugtest", "Application onCreate");
    }
    public void onTerminate()
    {
        super.onTerminate();
        Log.e("debugtest", "Application onTerminate");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("debugtest", "Application onConfigurationChanged");
    }
}
