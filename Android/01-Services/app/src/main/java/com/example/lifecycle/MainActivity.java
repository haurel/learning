package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonStart, buttonStop;
    boolean isServicesOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.button);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LifeCycleServices.class);
                i.putExtra("value", "SERVICE START");
                startService(i);
            }
        });

        buttonStop = findViewById(R.id.button2);
        buttonStop.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, LifeCycleServices.class));
            }
        }));
    }

    @Override
    protected void onPause() {
        super.onPause();
        isServicesOn = LifeCycleServices.ServicesRunning();
        if(isServicesOn) {
            Intent i = new Intent(MainActivity.this, LifeCycleServices.class);
            i.putExtra("value", "APPLICATION PAUSE");
            startService(i);
        }else{
            Toast.makeText(this, "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isServicesOn = LifeCycleServices.ServicesRunning();
        if(isServicesOn) {
            Intent i = new Intent(MainActivity.this, LifeCycleServices.class);
            i.putExtra("value", "APPLICATION RESUME");
            startService(i);
        }else{
            Toast.makeText(this, "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isServicesOn = LifeCycleServices.ServicesRunning();
        if(isServicesOn) {
            Intent i = new Intent(MainActivity.this, LifeCycleServices.class);
            i.putExtra("value", "APPLICATION STOP");
            startService(i);
        }else{
            Toast.makeText(this, "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isServicesOn = LifeCycleServices.ServicesRunning();
        if(isServicesOn) {
            Intent i = new Intent(MainActivity.this, LifeCycleServices.class);
            i.putExtra("value", "APPLICATION DESTROY");
            startService(i);
        }else{
            Toast.makeText(this, "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
        }
    }
}
