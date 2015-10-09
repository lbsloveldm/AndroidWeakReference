package com.example.administrator.weakrefrence;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    static MainActivity mainActivity;

    WeakReference<WeakOnClickListener> weakOnClickListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        weakOnClickListener = new WeakReference<WeakOnClickListener>(new WeakOnClickListener(this));
        findViewById(R.id.img_btn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(weakApplication.mApplicationContext, Test2Activity.class);
                        intent = new Intent(mainActivity, Test2Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        weakApplication.mApplicationContext.startActivity(intent);
                    }
                }
        );
        Log.e("debugtest", "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("debugtest", "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("debugtest", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("debugtest", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("debugtest", "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("debugtest", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("debugtest", "onPause");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.e("debugtest", "MainActivity finalize()");
    }
}
