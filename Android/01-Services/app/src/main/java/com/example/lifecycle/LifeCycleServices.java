package com.example.lifecycle;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class LifeCycleServices extends Service {
    private static LifeCycleServices instance = null;

    public static boolean ServicesRunning(){
        return instance != null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        instance = this;
        String text = intent.getExtras().getString("value");
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        instance = null;
        Toast.makeText(this, "SERVICE STOPPED", Toast.LENGTH_SHORT).show();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}