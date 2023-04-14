package com.example.dhillon_zachary_comp304_lab5
/*

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

object DAO {
    private val database = Firebase.database
    private val myRef = database.getReference("Movies")

    var liveMovieList: MutableLiveData<ArrayList<MovieData>>()

    init {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var temporaryList = arrayListOf<MovieData>()
                if (snapshot.exists()) {
                    for (movieSnap in snapshot.children) {
                        val snap = movieSnap.getValue<MovieData>()
                        temporaryList.add(snap!!)
                    }
                }
                liveMovieList.value = temporaryList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun insert(movie: MovieData) {
        val key = myRef.push().key
        movie.primaryId = key
        myRef.child(key!!).setValue(movie)
    }

    fun update(movie: MovieData) {
        myRef.child(movie.primaryId!!).setValue(movie)
    }

    fun delete(movie: MovieData) {
        myRef.child(movie.primaryId!!).removeValue()
    }
}
 */