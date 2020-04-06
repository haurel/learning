package com.example.serviceswithhashset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;

    Button startServices, stopServices, SetButton;
    Button UnionButton, IntersectionButton, DifferenceButton, SymetricButton;
    boolean isServicesOn;
    private static String[] multimeaA;
    private static int[] intA;
    EditText editTextA;
    private static String[] multimeaB;
    private static int[] intB;
    EditText editTextB;

    GetResultFromServices receiver;

    HashSet<Integer> _setA = new HashSet<Integer>();
    HashSet<Integer> _setB = new HashSet<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        startServices =findViewById(R.id.buttonStart);
        stopServices = findViewById(R.id.buttonStop);
        SetButton = findViewById(R.id.button7);

        UnionButton = findViewById(R.id.buttonUnion);
        IntersectionButton = findViewById(R.id.buttonIntersection);
        DifferenceButton = findViewById(R.id.buttonDifference);
        SymetricButton = findViewById(R.id.buttonSymetric);

        editTextA = (EditText) findViewById(R.id.editText);
        editTextB = (EditText) findViewById(R.id.editText2);



        startServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ServicesCalculate.class);
                startService(i);

            }
        });

        stopServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ServicesCalculate.ServicesRunning()){
                    stopService(new Intent(MainActivity.this, ServicesCalculate.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Service Not Running", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    multimeaA = editTextA.getText().toString().split(",");
                    multimeaB = editTextB.getText().toString().split(",");

                    for (String s : multimeaA) {
                        _setA.add(Integer.valueOf(s));
                    }
                    for (String s : multimeaB) {
                        _setB.add(Integer.valueOf(s));
                    }
                    Toast.makeText(getApplicationContext(), "Multimi setate", Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

        UnionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isServicesOn = ServicesCalculate.ServicesRunning();
                if(isServicesOn) {
                    Intent i = new Intent(MainActivity.this, ServicesCalculate.class);
                    i.putExtra("operation", "union");
                    i.putExtra("multimeaA", _setA);
                    i.putExtra("multimeaB", _setB);
                    startService(i);
                }else{
                    Toast.makeText(getApplicationContext(), "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
                }
            }
        });
        IntersectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isServicesOn = ServicesCalculate.ServicesRunning();
                if(isServicesOn) {
                    Intent i = new Intent(MainActivity.this, ServicesCalculate.class);
                    i.putExtra("operation", "intersection");
                    i.putExtra("multimeaA", _setA);
                    i.putExtra("multimeaB", _setB);
                    startService(i);
                }else{
                    Toast.makeText(getApplicationContext(), "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
                }
            }
        });
        DifferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isServicesOn = ServicesCalculate.ServicesRunning();
                if(isServicesOn) {
                    Intent i = new Intent(MainActivity.this, ServicesCalculate.class);
                    i.putExtra("operation", "difference");
                    i.putExtra("multimeaA", _setA);
                    i.putExtra("multimeaB", _setB);
                    startService(i);
                }else {
                    Toast.makeText(getApplicationContext(), "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SymetricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isServicesOn = ServicesCalculate.ServicesRunning();
                if(isServicesOn) {
                    Intent i = new Intent(MainActivity.this, ServicesCalculate.class);
                    i.putExtra("operation", "symmetric_difference");
                    i.putExtra("multimeaA", _setA);
                    i.putExtra("multimeaB", _setB);
                    startService(i);
                }else {
                    Toast.makeText(getApplicationContext(), "WITHOUT INFORMATION", Toast.LENGTH_SHORT).show();
                }
            }
        });

        receiver = new GetResultFromServices();
        registerReceiver(receiver, new IntentFilter("GET_UNION"));
        registerReceiver(receiver, new IntentFilter("GET_INTERSECTION"));
        registerReceiver(receiver, new IntentFilter("GET_DIFFERENCE"));
        registerReceiver(receiver, new IntentFilter("GET_SYMMETRIC_DIFFERENCE"));
    }

    public static MainActivity getInstace(){
        return instance;
    }

    public void updateTheTextView(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textV1 = (TextView) findViewById(R.id.textView3);
                textV1.setText(t);
            }
        });
    }
}

class GetResultFromServices extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String print = "Result:\n";
        Log.v("Print", print);
        if (intent.getAction().equals("GET_UNION") || intent.getAction().equals("GET_INTERSECTION")
                || intent.getAction().equals("GET_DIFFERENCE") || intent.getAction().equals("GET_SYMMETRIC_DIFFERENCE")) {
            HashSet<Integer> resultValue = (HashSet<Integer>) intent.getSerializableExtra("value");

            assert resultValue != null;
            for (Integer i : resultValue) {
                print += String.valueOf(i);
                print += "\n";
            }
            if (MainActivity.getInstace() != null) {

                MainActivity.getInstace().updateTheTextView(print);
            }
        }
    }
}
