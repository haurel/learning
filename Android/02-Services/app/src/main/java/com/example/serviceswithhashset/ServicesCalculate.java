package com.example.serviceswithhashset;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServicesCalculate extends Service {
    private static ServicesCalculate instance = null;
    private static int[] intA, intB;

    HashSet<Integer> _A = new HashSet<Integer>();
    HashSet<Integer> _B = new HashSet<Integer>();

    public static boolean ServicesRunning(){
        return instance != null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(instance == null) {
            instance = this;
            Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
        }else {
            String operation = intent.getExtras().getString("operation");
            _A = (HashSet<Integer>) intent.getSerializableExtra("multimeaA");
            _B = (HashSet<Integer>) intent.getSerializableExtra("multimeaB");
            if ("union".equals(operation)) {
                //CalculateUnion(intA, intB);
                HashSet<Integer> result = CalculateUnion(_A, _B);
                Intent sendToActivity = new Intent();
                sendToActivity.setAction("GET_UNION");
                sendToActivity.putExtra( "value", result);
                sendBroadcast(sendToActivity);
                Toast.makeText(getApplicationContext(), "Union", Toast.LENGTH_SHORT).show();
            }else if("intersection".equals(operation)){
                HashSet<Integer> result = CalculateIntersection(_A, _B);
                Intent sendToActivity = new Intent();
                sendToActivity.setAction("GET_INTERSECTION");
                //sendToActivity.setAction("GET_RESULT");
                sendToActivity.putExtra( "value", result);
                sendBroadcast(sendToActivity);
                Toast.makeText(getApplicationContext(), "Intersection", Toast.LENGTH_SHORT).show();
            }else if("difference".equals(operation)){
                HashSet<Integer> result = CalculateDifference(_A, _B);
                Intent sendToActivity = new Intent();
                sendToActivity.setAction("GET_DIFFERENCE");
                //sendToActivity.setAction("GET_RESULT");
                sendToActivity.putExtra( "value", result);
                sendBroadcast(sendToActivity);
                Toast.makeText(getApplicationContext(), "Difference", Toast.LENGTH_SHORT).show();
            }else if("symmetric_difference".equals(operation)){
                HashSet<Integer> result = CalculateSymmetricDifference(_A, _B);
                Intent sendToActivity = new Intent();
                sendToActivity.setAction("GET_SYMMETRIC_DIFFERENCE");
                //sendToActivity.setAction("GET_RESULT");
                sendToActivity.putExtra( "value", result);
                sendBroadcast(sendToActivity);
                Toast.makeText(getApplicationContext(), "Symmetric_Difference", Toast.LENGTH_SHORT).show();
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    private HashSet<Integer> CalculateSymmetricDifference(HashSet<Integer> _inputA, HashSet<Integer> _inputB) {
        HashSet<Integer> _resultA = CalculateDifference(_inputA, _inputB);
        HashSet<Integer> _resultB = CalculateDifference(_inputB, _inputA);
        return CalculateUnion(_resultA, _resultB);
    }

    private HashSet<Integer> CalculateDifference(HashSet<Integer> _inputA, HashSet<Integer> _inputB) {
        Integer[] _tempA = _inputA.toArray(new Integer[_inputA.size()]);
        Integer[] _tempB = _inputB.toArray(new Integer[_inputB.size()]);
        Integer[] _returnArray = new Integer[Math.max(_tempA.length, _tempB.length)];


        for (Integer value : _tempA) {
            Log.v("Array Value", "Array Value A" + value);
        }

        for (Integer value : _tempB) {
            Log.v("Array Value", "Array Value B" + value);
        }

        int pos = 0;
        for (Integer integer : _tempA) {
            boolean find = false;
            for (Integer value : _tempB) {
                if (integer.equals(value)) {
                    find = true;
                    break;
                }
            }
            if (!find && integer != null){
                _returnArray[pos] = integer;
                pos++;
            }
        }

        List<Integer> list = new ArrayList<Integer>();
        for(Integer i : _returnArray) {
            if(i != null) {
                list.add(i);
            }
        }
        _returnArray = list.toArray(new Integer[list.size()]);

        return new HashSet<>(Arrays.asList(_returnArray));
    }

    private HashSet<Integer> CalculateIntersection(HashSet<Integer> _inputA, HashSet<Integer> _inputB) {
        Integer[] _tempA = _inputA.toArray(new Integer[_inputA.size()]);
        Integer[] _tempB = _inputB.toArray(new Integer[_inputB.size()]);
        Integer[] _returnArray = new Integer[Math.max(_tempA.length, _tempB.length)];
        int pos = 0;
        for (Integer value : _tempA) {
            for (Integer integer : _tempB) {
                if (value.equals(integer)) {
                    _returnArray[pos] = value;
                    pos++;
                }
            }
        }
        return new HashSet<>(Arrays.asList(_returnArray));
    }

    private HashSet<Integer> CalculateUnion(HashSet<Integer> _inputA, HashSet<Integer> _inputB){
        HashSet<Integer> union = new HashSet<Integer>();

        Integer[] _tempA = _inputA.toArray(new Integer[_inputA.size()]);
        Integer[] _tempB = _inputB.toArray(new Integer[_inputB.size()]);
        Integer[] _returnArray = new Integer[_tempA.length + _tempB.length];

        for (Integer value : _tempA) {
            Log.v("Array Value", "Array Value A" + value);
        }

        for (Integer value : _tempB) {
            Log.v("Array Value", "Array Value B" + value);
        }
        System.arraycopy(_tempA, 0, _returnArray, 0, _tempA.length);

        int pos = _tempA.length;
        for (Integer integer : _tempB) {
            boolean find = false;
            for (Integer value : _tempA) {
                if (integer.equals(value)) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                _returnArray[pos] = integer;
                pos++;
            }
        }
        for (Integer value : _returnArray) {
            Log.v("Array Value", "Array Value" + value);
        }
        return new HashSet<>(Arrays.asList(_returnArray));

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Service Stoped", Toast.LENGTH_SHORT).show();
        instance = null;

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}