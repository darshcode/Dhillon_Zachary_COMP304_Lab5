package com.example.dhillon_zachary_comp304_lab5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val TAG = "AttentionTag"
    private lateinit var auth: FirebaseAuth
    private lateinit var email: TextView
    private lateinit var pass: TextView
    private lateinit var login: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables
        email = findViewById(R.id.id_TxtUser)
        pass = findViewById(R.id.id_TxtPass)
        //login = findViewById(R.id.id_BtnLogin)
        // Write message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        var email_val = email.text.toString()
        var pass_val = email.text.toString()


        auth.createUserWithEmailAndPassword("darsh11", "dhillon11")

//        login.setOnClickListener {
//            auth.signInWithEmailAndPassword(email_val, pass_val)
//        }




        //To Set list
        //myRef.setValue("Hello, World!")

        //To Append to list
        //myRef.push().setValue("enter something3") --->to use this change hashset on line 36

        // To Read from the database
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })


    }
}