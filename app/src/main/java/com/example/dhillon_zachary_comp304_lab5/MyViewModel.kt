package com.example.dhillon_zachary_comp304_lab5
/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel(private val repository: MyRepository) : ViewModel() {

    var movieList: LiveData<ArrayList<MovieData>> = repository.allMovies


    fun insert(movie: MovieData) = viewModelScope.launch {
        repository.insert(movie)
    }

    fun update(input: String, newInput: String) {
        viewModelScope.launch {
            for (movie in movieList.value!!) {
                if (movie.movieName == input) {
                    movie.movieName = newInput
                    repository.update(movie)
                }
            }
        }
    }

    fun delete(input: String) {
        viewModelScope.launch {
            for (p in movieList.value!!) {
                if (p.movieName == input) {
                    repository.delete(p)
                }
            }
        }
    }

}

 */