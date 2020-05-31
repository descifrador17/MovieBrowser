package com.descifrador.moviebrowser.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.descifrador.moviebrowser.R
import com.descifrador.moviebrowser.network.Result
import java.time.LocalDateTime


class MoviesAdapter(private val movies: List<Result>): RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        return holder.bind(movies[position])
    }

}

class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val photo:ImageView = itemView.findViewById(R.id.movie_photo)
    private val title:TextView = itemView.findViewById(R.id.movie_title)
    private val overview:TextView = itemView.findViewById(R.id.movie_overview)
    private val rating:TextView = itemView.findViewById(R.id.movie_rating)

    @SuppressLint("SetTextI18n")
    fun bind(movie: Result) {

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w342/${movie.backdrop_path}")
            .error(R.drawable.no_image)
            .into(photo)

        title.text = "Title: " + movie.title

        when (movie.overview) {
            "" -> overview.visibility = View.GONE
            else -> overview.text = movie.overview
        }

        when (movie.vote_average) {
            0.0 -> rating.text = "Rating : Not yet Rated"
            else -> rating.text = "Rating : ${movie.vote_average}"
        }

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MovieDetails::class.java)
            intent.putExtra("movie_id", movie.id)
            itemView.context.startActivity(intent)
        }
    }
}