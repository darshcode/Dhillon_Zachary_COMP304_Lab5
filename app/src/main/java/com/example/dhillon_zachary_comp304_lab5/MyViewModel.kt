package com.example.dhillon_zachary_comp304_lab5

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class MyViewModel(private val repository: MyRepository) : ViewModel() {

    var movieList: LiveData<ArrayList<MovieData>> = repository.allMovies

    fun insert(movie: MovieData) = viewModelScope.launch {
        repository.insert(movie)
    }

    fun update(movie1: String, movie: MovieData) = viewModelScope.launch {
        repository.update(movie)
    }

    fun delete(movieName: String) {
        viewModelScope.launch {
            repository.delete(movieName)
        }
    }

}
