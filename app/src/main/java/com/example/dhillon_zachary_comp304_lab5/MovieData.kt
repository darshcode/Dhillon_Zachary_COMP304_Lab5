package com.example.dhillon_zachary_comp304_lab5

//Creating Class To Store Firebase Objects
data class MovieData (
    var movieName: String = "",
    var movieYear: Int = 0,
    var movieRating: Double = 0.0,
    var movieGenre: String = "",
    var primaryId: String? = null
)