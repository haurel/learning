package com.example.kotlinno1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //scapam de findViewById(R.id.texViewTest) ca in java
        textViewTest.text = "Hello World from Kotlin"

        button.setOnClickListener(){
            Toast.makeText(applicationContext, "Toast Message", Toast.LENGTH_LONG).show();


            val toast = Toast.makeText(applicationContext, "Toast with variable", Toast.LENGTH_SHORT)
            toast.show()

            val toast2 = Toast.makeText(applicationContext, "Toast with Gravity", Toast.LENGTH_LONG)
            toast2.setGravity(Gravity.CENTER, 200, 200)
            toast2.show()
        }
        val list = listOf(1, 2, 3, 4)
        for(k in list){
            Log.d("listOf", k.toString())
        }

        val set = setOf(1, 2, 3, 4)
        for(k in set){
            Log.d("setOf", k.toString())
        }

        val string = "print my characters"
        for(char in string){
            Log.d("string", "$char ")
        }

        val person1 = Person("Alex", "Smith")
        val person2 = Person("Alex", "Smith", 29)

        person1.print()
        person2.print()
        //val person3 = Person("", "Smith")
        //val person4 = Person("Alex", "", 29)
        //val person5 = Person("Alex", "Smith", -1)
    }

}


class Person constructor(private val firstName: String, private val lastName: String, private val age: Int?=25){
    init{
        require(firstName.trim().isNotEmpty()){ "Invalid firstName argument" }
        require(lastName.trim().isNotEmpty()){ "Invalid lastName argument" }
        if(age != null){
            require(age in 0..100) { "Invalid age argument"}
        }
    }
    public fun print(){
        Log.d("FirstName", firstName)
        Log.d("LastName", lastName)
        Log.d("Age", age.toString())
    }
}

