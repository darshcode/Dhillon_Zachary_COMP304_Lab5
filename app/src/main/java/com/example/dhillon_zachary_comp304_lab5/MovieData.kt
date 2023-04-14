package com.example.dhillon_zachary_comp304_lab5

//Creating Class To Store Firebase Objects
data class MovieData (
    val movieName: String = "",
    val movieYear: Int = 0,
    val movieRating: Double = 0.0,
    val movieGenre: String = "",
    var primaryId: String? = null
)