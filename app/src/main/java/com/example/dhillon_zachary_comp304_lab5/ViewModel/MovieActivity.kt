package com.example.dhillon_zachary_comp304_lab5.ViewModel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.dhillon_zachary_comp304_lab5.MainActivity
import com.example.dhillon_zachary_comp304_lab5.R
import com.example.dhillon_zachary_comp304_lab5.ViewModel.MovieData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MovieActivity : AppCompatActivity() {

    //Declaring Variables For Movie Details
    private lateinit var movieName: EditText
    private lateinit var movieYear: EditText
    private lateinit var movieRating: EditText
    private lateinit var movieGenre: EditText

    //Declaring Variables For Buttons
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        //Binding Variables To UI Elements
        btnAdd = findViewById(R.id.submit)
        btnUpdate = findViewById(R.id.edit)
        btnDelete = findViewById(R.id.delete)
        btnBack = findViewById(R.id.go_back)

        //Getting Movie Details From Edit Texts
        movieName = findViewById(R.id.et_movie_name)
        movieYear = findViewById(R.id.et_movie_year)
        movieRating = findViewById(R.id.et_movie_ratings)
        movieGenre = findViewById(R.id.et_movie_genre)

        //Adding Reference To Firebase Database
        val database = Firebase.database
        val myRef = database.getReference("Movies")

        //Adding Movie To Database
        btnAdd.setOnClickListener {
            val movie = MovieData(movieName.text.toString(), movieYear.text.toString().toInt(), movieRating.text.toString().toDouble(), movieGenre.text.toString())
            myRef.push().setValue(movie)
        }

        //Updating Movie In Database
        btnUpdate.setOnClickListener {
            val movie = MovieData(movieName.text.toString(), movieYear.text.toString().toInt(), movieRating.text.toString().toDouble(), movieGenre.text.toString())
            myRef.push().setValue(movie)
        }

        //Deleting Movie From Database
        btnDelete.setOnClickListener {
            val movie = MovieData(movieName.text.toString(), movieYear.text.toString().toInt(), movieRating.text.toString().toDouble(), movieGenre.text.toString())
            myRef.push().setValue(movie)
        }

        //Create Intent To Go Back To Main Activity
        btnBack.setOnClickListener {
            val intent = Intent(this@MovieActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}