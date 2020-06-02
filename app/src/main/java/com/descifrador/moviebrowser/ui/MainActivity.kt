package com.descifrador.moviebrowser.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.descifrador.moviebrowser.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popularMoviesCard.setOnClickListener {
            val intent : Intent = Intent(this,Pages::class.java)
            intent.putExtra("search","popular")
            intent.putExtra("title","Popular Movies")
            this.startActivity(intent)
        }

        upcomingMoviesCard.setOnClickListener {
            val intent : Intent = Intent(this,Pages::class.java)
            intent.putExtra("search","upcoming")
            intent.putExtra("title","Upcoming Movies")
            this.startActivity(intent)
        }

        topRatedMoviesCard.setOnClickListener {
            val intent : Intent = Intent(this,Pages::class.java)
            intent.putExtra("search","top_rated")
            intent.putExtra("title","Top Rated Movies")
            this.startActivity(intent)
        }

        moviesInTheatreCard.setOnClickListener {
            val intent : Intent = Intent(this,Pages::class.java)
            intent.putExtra("search","now_playing")
            intent.putExtra("title","Running In Theatres")
            this.startActivity(intent)
        }
    }

}