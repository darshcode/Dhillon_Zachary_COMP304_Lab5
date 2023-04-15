package com.example.dhillon_zachary_comp304_lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MovieActivity : AppCompatActivity() {

    // Declaring Variables For Movie Details
    private lateinit var moviesList: TextView
    private lateinit var movieName: EditText
    private lateinit var movieYear: EditText
    private lateinit var movieRating: EditText
    private lateinit var movieGenre: EditText

    // Declaring Variables For Buttons
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnBack: Button

    // Instantiate the ViewModel
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        moviesList = findViewById(R.id.id_Movie_moviesList)

        // Binding Variables To UI Elements
        btnAdd = findViewById(R.id.submit)
        btnUpdate = findViewById(R.id.edit)
        btnDelete = findViewById(R.id.delete)
        btnBack = findViewById(R.id.go_back)

        // Getting Movie Details From Edit Texts
        movieName = findViewById(R.id.et_movie_name)
        movieYear = findViewById(R.id.et_movie_year)
        movieRating = findViewById(R.id.et_movie_ratings)
        movieGenre = findViewById(R.id.et_movie_genre)

        // Observe changes in the movie list and update the UI
        viewModel.movieList.observe(this, Observer { movieList ->
            val stringBuilder = StringBuilder()
            for (movie in movieList) {
                stringBuilder.append("${movie.movieName} (${movie.movieYear}) - ${movie.movieYear} - Rating: ${movie.movieRating}\n\n")
            }
            moviesList.text = stringBuilder.toString()
        })

        // Adding Movie To Database
        btnAdd.setOnClickListener {
            val movie = MovieData(movieName.text.toString(), movieYear.text.toString().toInt(), movieRating.text.toString().toDouble(), movieGenre.text.toString())
            viewModel.insert(movie)
        }

        // Updating Movie In Database
        btnUpdate.setOnClickListener {
            val newName = movieName.text.toString()
            val newYear = movieYear.text.toString().toInt()
            val newRating = movieRating.text.toString().toDouble()
            val newGenre = movieGenre.text.toString()
            val updatedMovie = MovieData(newName, newYear, newRating, newGenre)
            viewModel.update(movieName.text.toString(), updatedMovie)
        }

        // Deleting Movie From Database
        btnDelete.setOnClickListener {
            viewModel.delete(movieName.text.toString())
        }

        // Create Intent To Go Back To Main Activity
        btnBack.setOnClickListener {
            val intent = Intent(this@MovieActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}


