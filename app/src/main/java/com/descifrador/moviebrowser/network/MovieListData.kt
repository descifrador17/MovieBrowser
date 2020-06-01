package com.descifrador.moviebrowser.network

data class MovieListData(
    val results: List<Result>,
    val total_pages : Int
)

data class Result(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    val backdrop_path : String
)