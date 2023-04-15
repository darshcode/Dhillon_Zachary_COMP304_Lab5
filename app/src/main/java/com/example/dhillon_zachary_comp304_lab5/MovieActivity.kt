package com.example.dhillon_zachary_comp304_lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MovieActivity : AppCompatActivity() {

    //Declaring Variables For Movie Details
    private lateinit var moviesList :TextView
    private lateinit var movieName: EditText
    private lateinit var movieYear: EditText
    private lateinit var movieRating: EditText
    private lateinit var movieGenre: EditText


    //Declaring Variables For Buttons
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnBack: Button

    //Adding Reference To Firebase Database
    private val database = Firebase.database
    private var myRef = database.getReference("Movies")

    //Displaying Movies
    private fun displayMovies() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val stringBuilder = StringBuilder()
                for (snapshot in dataSnapshot.children) {
                    val movie = snapshot.getValue(MovieData::class.java)
                    if (movie != null) {
                        stringBuilder.append("${movie.movieName} (${movie.movieYear}) - ${movie.movieYear} - Rating: ${movie.movieRating}\n\n")
                    }
                }
                moviesList.text = stringBuilder.toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Error", "Failed to read value.", databaseError.toException())
            }
        })
    }

    //Update Movie
    private fun updateMovie(movieName: String, updatedMovie: MovieData) {
        myRef.orderByChild("name").equalTo(movieName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    snapshot.ref.setValue(updatedMovie)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Error", "Failed to update movie.", databaseError.toException())
            }
        })
    }

    //Delete Movie
    private fun deleteMovie(movieName: String) {
        myRef.orderByChild("name").equalTo(movieName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    snapshot.ref.removeValue()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Error", "Failed to delete movie.", databaseError.toException())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        moviesList = findViewById(R.id.id_Movie_moviesList)
        val allMovies:MutableLiveData<ArrayList<MovieData>>

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

        val database = Firebase.database
        myRef = database.getReference("Movies")

        displayMovies()

        //Adding Movie To Database
        btnAdd.setOnClickListener {
            val movie = MovieData(movieName.text.toString(), movieYear.text.toString().toInt(), movieRating.text.toString().toDouble(), movieGenre.text.toString())
            myRef.push().setValue(movie)
        }

        //Updating Movie In Database
        btnUpdate.setOnClickListener {
            val updatedMovie = MovieData(movieName.text.toString(), movieYear.text.toString().toInt(), movieRating.text.toString().toDouble(), movieGenre.text.toString())
            updateMovie(movieName.text.toString(), updatedMovie)
        }

        //Deleting Movie From Database
        btnDelete.setOnClickListener {
            deleteMovie(movieName.text.toString())
        }

        //Create Intent To Go Back To Main Activity
        btnBack.setOnClickListener {
            val intent = Intent(this@MovieActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}

