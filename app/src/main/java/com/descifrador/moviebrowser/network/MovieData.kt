package com.descifrador.moviebrowser.network

import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)


data class Genre(
    val name: String
)