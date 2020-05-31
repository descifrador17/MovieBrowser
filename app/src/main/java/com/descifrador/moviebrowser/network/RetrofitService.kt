package com.descifrador.moviebrowser.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/3/movie/upcoming")
    fun getMovies(@Query("api_key") key: String,@Query("page")page:Int): Observable<MovieListData>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id : Int,@Query("api_key") key: String) : Observable<MovieData>


}