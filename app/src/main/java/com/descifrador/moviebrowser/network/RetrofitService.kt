package com.descifrador.moviebrowser.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/3/movie/{search}")
    fun getMovies(@Path("search") search : String,@Query("api_key") key: String, @Query("page")page:Int): Observable<MovieListData>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id : Int,@Query("api_key") key: String) : Observable<MovieData>

    @GET("/3/search/movie")
    fun getSearchResults(@Query("api_key") key : String,@Query("query") movie_name : String,@Query("page") page : Int) : Observable<MovieListData>

    @GET("/3/movie/{movie_id}/similar")
    fun getSimilarResults(@Path("movie_id") id : Int,@Query("api_key") key : String,@Query("page") page : Int) : Observable<MovieListData>

}