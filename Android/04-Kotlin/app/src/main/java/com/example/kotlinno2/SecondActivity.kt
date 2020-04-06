package com.example.kotlinno2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val value = intent.getStringExtra("Value")

        val nametxt = findViewById<EditText>(R.id.editText2)
        //nametxt.text = Editable.Factory.getInstance().newEditable(value)

        nametxt.setText(value)

        //editText2.setText(intent.getStringExtra("Value"))
        //editText2.text = Editable.Factory.getInstance().newEditable(
        //    value
        //)
        button2?.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Value", editText2.text.toString())

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
