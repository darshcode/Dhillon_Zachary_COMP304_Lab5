package com.example.dhillon_zachary_comp304_lab5
/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel(private val repository: Repository) : ViewModel(){

    var movieList : LiveData<ArrayList<MovieData>> = repository.allMovies


    fun insert(laptop: MovieData) = viewModelScope.launch {
        repository.insert(laptop)
    }

    fun update(input: String, newInput: String){
        viewModelScope.launch {
            for(laptop in movieList.value!!){
                if(laptop.movieName == input){
                    laptop.movieName = newInput
                    repository.update(laptop)
                }
            }
        }
    }

    fun delete(input: String){
        viewModelScope.launch {
            for(p in movieList.value!!){
                if(p.movieName == input){
                    repository.delete(p)
                }
            }
        }
    }

}*/