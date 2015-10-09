package com.qihoo.workthread;

import android.app.Activity;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2015/10/9.
 */
public class WorkThread extends Thread {
    public static WorkThread workThread = new WorkThread();
    public boolean bInited = false;

    public int refrenceType = -1; // 1: 强引用、2：软引用、3：弱引用 4： 若引用list  5：若引用map

    List<Activity> strongList = new ArrayList<>();
    SoftReference<Activity> sr;
    WeakReference<Activity> wr;
    List<WeakReference<Activity>> weakList = new ArrayList<WeakReference<Activity>>();
    WeakHashMap<Activity, Integer> weakMap = new WeakHashMap<Activity, Integer>();

    public void add(Activity activity) {
        synchronized (strongList) {
            addImp(activity);
        }
    }

    public void addImp(Activity activity) {
        if (refrenceType == 1) {
            synchronized (strongList) {
                strongList.add(activity);
            }
        } else if (refrenceType == 2) {
            sr = new SoftReference<Activity>(activity);
        } else if (refrenceType == 3) {
            wr = new WeakReference<Activity>(activity);
        } else if (refrenceType == 4) {
            synchronized (weakList) {
                weakList.add(new WeakReference<Activity>(activity));
            }
        } else if (refrenceType == 5) {
            synchronized (weakMap) {
                weakMap.put(activity, 0);
            }
        }
    }

    public void del(Activity activity) {
        synchronized (strongList) {
            for (int n = 0; n < strongList.size(); ++n) {
                strongList.remove(n);
            }
        }
        sr = null;
        wr = null;
        synchronized (weakList) {
            for (int n = 0; n < weakList.size(); ++n) {
                weakList.remove(n);
            }
        }
        synchronized (weakMap) {
            Iterator<Map.Entry<Activity, Integer>> iterator = weakMap.entrySet().iterator();
            while (iterator.hasNext()) {
                iterator.remove();
            }
        }
    }

    public void startWork() {
        if (!bInited) {
            bInited = true;
            start();
        }
    }

    @Override
    public void run() {
        Thread.currentThread().setName("testtesttest");
        for (int ii = 0; ii < 160; ++ii) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test(ii);
        }
    }

    private void test(int m) {

        System.gc();

        synchronized (strongList) {
            if (refrenceType == 1) {
                synchronized (strongList) {
                    Log.e("debugtest", "强引用 " + m + " " + strongList.size());
                }
            } else if (refrenceType == 2) {
                if (sr.get() == null) {
                    Log.e("debugtest", "软引用 " + m + " " + sr.get());
                } else {
                    Log.e("debugtest", "软引用 " + m + " " + sr.get().toString());
                }
            } else if (refrenceType == 3) {
                if (wr.get() == null) {
                    Log.e("debugtest", "弱引用 " + m + " " + wr.get());
                } else {
                    Log.e("debugtest", "弱引用 " + m + " " + wr.get().toString());
                }
            } else if (refrenceType == 4) {
                synchronized (weakList) {
                    for (int n = 0; n < weakList.size(); ++n) {
                        Log.e("debugtest", "弱引用 List " + m + " " + weakList.get(n).get());
                    }
                }
            } else if (refrenceType == 5) {
                synchronized (weakMap) {
                    Iterator<Map.Entry<Activity, Integer>> iterator = weakMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<Activity, Integer> entry = iterator.next();
                        Log.e("debugtest", "弱引用 weakHashMap " + m + " " + entry.getKey() + " " + entry.getValue());
                    }
                }
            }
        }
    }
}
