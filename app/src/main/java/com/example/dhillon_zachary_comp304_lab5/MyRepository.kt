
package com.example.dhillon_zachary_comp304_lab5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyRepository {
    private val database = FirebaseDatabase.getInstance()
    private val movieRef = database.getReference("Movies")
    private val _allMovies = MutableLiveData<ArrayList<MovieData>>()
    val allMovies: LiveData<ArrayList<MovieData>> = _allMovies

    init {
        movieRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val movieList = ArrayList<MovieData>()
                for (snapshot in dataSnapshot.children) {
                    val movie = snapshot.getValue(MovieData::class.java)
                    if (movie != null) {
                        movieList.add(movie)
                    }
                }
                _allMovies.postValue(movieList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }

    suspend fun insert(movie: MovieData) = withContext(Dispatchers.IO) {
        movieRef.push().setValue(movie)
    }

    suspend fun update(movie: MovieData) = withContext(Dispatchers.IO) {
        movieRef.child(movie.movieName).setValue(movie)
    }

    suspend fun delete(movie: MovieData) = withContext(Dispatchers.IO) {
        movieRef.child(movie.movieName).removeValue()
    }
}
