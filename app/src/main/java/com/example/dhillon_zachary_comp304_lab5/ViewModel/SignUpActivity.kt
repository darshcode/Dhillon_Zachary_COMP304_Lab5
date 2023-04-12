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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    // Initialize Firebase Authentication
    private lateinit var auth: FirebaseAuth

    // Declare UI elements
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvLogin: TextView
    private lateinit var btnBack: Button

    private val TAG = "Firebase_Auth_SignUp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Authentication
        auth = Firebase.auth

        // Bind UI elements to variables
        etEmail = findViewById(R.id.et_Email)
        etPassword = findViewById(R.id.et_Password)
        etConfirmPassword = findViewById(R.id.et_password_confirm)
        btnSignUp = findViewById(R.id.btn_signup)
        tvLogin = findViewById(R.id.bt_login)

        // Set click listener for Sign Up button
        btnSignUp.setOnClickListener {
            createUser()
        }

        // Set click listener for Login button
        tvLogin.setOnClickListener{
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        btnBack = findViewById(R.id.go_back)
        //Create Intent To Go Back To Main Activity
        btnBack.setOnClickListener {
            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Method to create a new user
    private fun createUser() {
        // Get input values from UI elements
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        // Validate input values
        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Email cannot be empty"
            etEmail.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            etPassword.error = "Password cannot be empty"
            etPassword.requestFocus()
        } else if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.error = "Confirm Password cannot be empty"
            etConfirmPassword.requestFocus()
        } else if (password != confirmPassword) {
            etConfirmPassword.error = "Passwords don't match"
            etConfirmPassword.requestFocus()
        } else {
            // If input values are valid, create a new user with Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // If user creation is successful, display a success message to the user
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(
                            this@SignUpActivity,
                            "Sign Up Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Navigate the user to the Login screen
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If user creation fails, display an error message to the user and log the error message
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed: " + task.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
