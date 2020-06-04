package com.descifrador.moviebrowser.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.NumberFormat.getNumberInstance
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.descifrador.moviebrowser.R
import com.descifrador.moviebrowser.network.MovieData
import com.descifrador.moviebrowser.network.ServiceBuilder
import com.descifrador.moviebrowser.util.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.text.NumberFormat
import java.text.NumberFormat.getNumberInstance
import java.time.LocalDateTime
import java.util.*


class MovieDetails : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra("movie_id",0)


        val moviecompositeDisposable = CompositeDisposable()
        moviecompositeDisposable.add(
            ServiceBuilder.buildService()
                .getMovieDetails(movieId,api_key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))

        similarMoviesButton.setOnClickListener{
            val intent = Intent(this,SimilarMovies::class.java)
            intent.putExtra("movieID",movieId)
            this.startActivity(intent)
        }
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun onResponse(response: MovieData) {
        progressmoviedetails.visibility = GONE

        movieTitleView.text = response.title
        title = response.title

        movieTitleView.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/#q=$title")
                )
            ) }

        when (response.posterPath) {
            "null" -> movieImageView.visibility = GONE
            else -> Glide.with(this).load("https://image.tmdb.org/t/p/w342/${response.posterPath}").into(movieImageView)
        }

        movieImageView.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/#q=$title")
                )
            ) }

        when(response.tagline){
            "" -> taglineView.visibility = GONE
            else -> taglineView.text = response.tagline

        }

        when(response.voteAverage){
            0.0 -> ratingView.text = "Rating : Not yet Rated"
            else -> ratingView.text = "Rating : ${response.voteAverage}"
        }

        when{
            response.releaseDate > LocalDateTime.now().toString() -> releaseDateView.text = "Release Date : ${response.releaseDate} \nStay tuned for more updates :)"
            else -> releaseDateView.text = "Release Date : ${response.releaseDate}"
        }

        when(response.runtime){
            0 -> runtimeView.visibility = GONE
            else -> runtimeView.text = "Runtime : ${response.runtime/60} hr ${response.runtime%60} min"
        }

        when(response.budget){
            0 -> budgetView.visibility = GONE
            else -> {
                val formattedBudget : String = NumberFormat.getNumberInstance(Locale.getDefault()).format(response.budget)
                budgetView.text = "Budget : $formattedBudget USD"
            }
        }

        when(response.revenue){
            0 -> revenueView.visibility = GONE
            else -> {
                val formattedRevenue : String = NumberFormat.getNumberInstance(Locale.getDefault()).format(response.revenue)
                revenueView.text = "Revenue : $formattedRevenue USD"
            }
        }

        when{
            response.genres.isNullOrEmpty()-> genreView.visibility = GONE
            else -> {
                var moviegenres = "Genre : "
                for(genre in response.genres ){
                    moviegenres += "${genre.name}  "
                }
                genreView.text = moviegenres
            }
        }

        when(response.overview){
            "" -> overviewView.visibility = GONE
            else -> overviewView.text = "Overview : \n${response.overview}"
        }
    }
 }
