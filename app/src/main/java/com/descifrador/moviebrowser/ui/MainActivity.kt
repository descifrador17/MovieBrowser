package com.descifrador.moviebrowser.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.descifrador.moviebrowser.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_screen_toolbar.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.home_screen_toolbar)


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

        searchMovieView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent : Intent = Intent(this@MainActivity,SearchResults::class.java)
                intent.putExtra("search_query",query)
                this@MainActivity.startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

}