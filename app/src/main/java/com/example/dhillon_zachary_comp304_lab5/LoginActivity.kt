package com.example.dhillon_zachary_comp304_lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView
    private val tag: String = "Firebase_Auth_Login"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        Log.d(tag, "IS THERE A USER? "+ auth.currentUser)

        etEmail = findViewById(R.id.id_Login_Email)
        etPassword = findViewById(R.id.id_Login_Password)
        btnLogin = findViewById(R.id.id_Login_btn)
        tvSignUp = findViewById(R.id.id_Login_signupBtn)

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
                            Log.d(tag, "signInWithEmail:success ${user.email}")
                        }
                        val intent = Intent(this@LoginActivity, MovieActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(tag, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}