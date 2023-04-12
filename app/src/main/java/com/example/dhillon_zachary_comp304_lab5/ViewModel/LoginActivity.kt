package com.example.dhillon_zachary_comp304_lab5.ViewModel

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dhillon_zachary_comp304_lab5.MainActivity
import com.example.dhillon_zachary_comp304_lab5.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    // Initialize Firebase authentication
    private lateinit var auth: FirebaseAuth

    // Declare UI elements
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnBack: Button

    // Declare a constant tag for logging
    private val TAG = "Firebase_Auth_Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize the Firebase authentication instance
        auth = FirebaseAuth.getInstance()

        // Initialize UI elements
        etEmail = findViewById(R.id.et_Email)
        etPassword = findViewById(R.id.et_Password)
        btnLogin = findViewById(R.id.bt_login)

        // Set up the click listener for the login button
        btnLogin.setOnClickListener {
            loginUser()
        }

        btnBack = findViewById(R.id.go_back)
        //Create Intent To Go Back To Main Activity
        btnBack.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loginUser() {
        // Get the email and password from the text fields
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        // Validate the email and password fields
        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Email cannot be empty"
            etEmail.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            etPassword.error = "Password cannot be empty"
            etPassword.requestFocus()
        } else {
            // Authenticate the user with Firebase authentication
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(
                            this@LoginActivity,
                            "Authentication successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@LoginActivity,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}

