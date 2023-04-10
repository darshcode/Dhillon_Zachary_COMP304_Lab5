package com.example.dhillon_zachary_comp304_lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step 4
        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        //appending values.
        //myRef.push().setValue("enter something")

        myRef.setValue("Hello, World!")





    }
}