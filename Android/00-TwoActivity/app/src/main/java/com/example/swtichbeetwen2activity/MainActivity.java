package com.example.swtichbeetwen2activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    String string;

    String tag = "Lifecycle Step";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                string = editText.getText().toString();
                intent.putExtra("Value", string);
                //startActivity(intent);
                startActivityForResult(intent, 1);
                //finish();
            }
        });
    }

    public void onPause(){
        super.onPause();
        Log.d(tag,"On pause");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getExtras().getString("Value");
                editText.setText(result);
            }
        }
    }
    /*
    public void onClick(View view){
        startActivity(new Intent("com.example.laborator3.SecondActivity"));

    }*/

}
