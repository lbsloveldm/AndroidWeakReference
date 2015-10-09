package com.example.administrator.weakrefrence;

import android.app.Activity;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2015/10/8.
 */
public class WeakOnClickListener implements View.OnClickListener {

    public MainActivity activity;

    public WeakOnClickListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.e("debugtest", "WeakOnClickListener finalize() " + hashCode());
    }
}
