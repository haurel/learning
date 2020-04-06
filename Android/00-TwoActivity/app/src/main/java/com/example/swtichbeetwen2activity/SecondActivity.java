package com.example.swtichbeetwen2activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    EditText editTextS;
    String stringS;
    Button buttonS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextS = findViewById(R.id.editText2);
        buttonS= findViewById(R.id.button2);

        stringS = getIntent().getExtras().getString("Value");
        editTextS.setText(stringS);

        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                stringS = editTextS.getText().toString();
                intent.putExtra("Value", stringS);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
