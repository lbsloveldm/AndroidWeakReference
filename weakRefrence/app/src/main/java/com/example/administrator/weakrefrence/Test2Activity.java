package com.example.administrator.weakrefrence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.qihoo.workthread.WorkThread;

public class Test2Activity extends AppCompatActivity {

    Test2Activity test2Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        test2Activity = this;

        findViewById(R.id.btn_strong_reference).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkThread.workThread.del(test2Activity);

                WorkThread.workThread.refrenceType = 1;
                WorkThread.workThread.add(test2Activity);
            }
        });

        findViewById(R.id.btn_soft_reference).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkThread.workThread.del(test2Activity);
                WorkThread.workThread.refrenceType = 2;
                WorkThread.workThread.add(test2Activity);
            }
        });

        findViewById(R.id.btn_weak_reference).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkThread.workThread.del(test2Activity);
                WorkThread.workThread.refrenceType = 3;
                WorkThread.workThread.add(test2Activity);
            }
        });

        findViewById(R.id.btn_weak_reference_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkThread.workThread.del(test2Activity);
                WorkThread.workThread.refrenceType = 4;
                WorkThread.workThread.add(test2Activity);
            }
        });

        findViewById(R.id.btn_weak_reference_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkThread.workThread.del(test2Activity);
                WorkThread.workThread.refrenceType = 5;
                WorkThread.workThread.add(test2Activity);
            }
        });

        WorkThread.workThread.startWork();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        WorkThread.workThread.del(test2Activity);

        test2Activity = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test2, menu);
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
        Log.e("debugtest", "Test2Activity：finalize() " + this);
    }
}
