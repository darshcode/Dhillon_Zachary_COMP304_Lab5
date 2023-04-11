package com.example.dhillon_zachary_comp304_lab5.ViewModel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dhillon_zachary_comp304_lab5.MainActivity
import com.example.dhillon_zachary_comp304_lab5.R
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView
    private val TAG = "Firebase_Auth_Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //auth = Firebase.auth
        Log.d(TAG, "IS THERE A USER? "+auth.currentUser)

        etEmail = findViewById(R.id.id_TxtUser)
        etPassword = findViewById(R.id.id_TxtPass)
        btnLogin = findViewById(R.id.id_BtnLogin)
        tvSignUp = findViewById(R.id.tv_signup)

        btnLogin.setOnClickListener {
            attemptLogin()
        }
        tvSignUp.setOnClickListener{
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun attemptLogin() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Email cannot be empty"
            etEmail.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            etPassword.error = "Password cannot be empty"
            etPassword.requestFocus()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser
                        if (user != null) {
                            Log.d(TAG, "signInWithEmail:success ${user.email}")
                        }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}