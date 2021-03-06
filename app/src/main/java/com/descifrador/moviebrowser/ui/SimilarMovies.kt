package com.descifrador.moviebrowser.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.descifrador.moviebrowser.R
import com.descifrador.moviebrowser.network.MovieListData
import com.descifrador.moviebrowser.network.ServiceBuilder
import com.descifrador.moviebrowser.util.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pages.*
import kotlinx.android.synthetic.main.custom_titlebar.*

private var movie_id = 0
private var CURRENT_PAGE = 1


class SimilarMovies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_similar_movies)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_titlebar)

        titleView.text = "Similar Movies"

        movie_id = intent.getIntExtra("movieID",0)

        val page = intent.getIntExtra("page",1)

        settingupcall(page)


        forward.setOnClickListener{
            val intent = Intent(this,SimilarMovies::class.java)
            intent.putExtra("page", CURRENT_PAGE+1)
            intent.putExtra("movieID", movie_id)
            this.startActivity(intent)
        }

        backward.setOnClickListener {
            val intent = Intent(this,SimilarMovies::class.java)
            intent.putExtra("page", CURRENT_PAGE-1)
            intent.putExtra("movieID", movie_id)
            this.startActivity(intent)
        }

        homeButtonView.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            this.startActivity(intent)
        }

    }

    private fun onFailure(t: Throwable) {
        object : CountDownTimer(5000,1000) {

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                progress_bar.visibility = View.GONE
                val title = findViewById<TextView>(R.id.title)
                title.text = "Something went Wrong!!"

            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }.start()

        Toast.makeText(this,t.message, Toast.LENGTH_SHORT).show()

    }

    private fun onResponse(response: MovieListData) {
        CURRENT_PAGE = response.page
        if(response.total_pages>1)
            forward.visibility = View.VISIBLE
        if(response.page >1)
            backward.visibility = View.VISIBLE
        if(response.page == response.total_pages)
            forward.visibility = View.GONE

        progress_bar.visibility = View.GONE

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SimilarMovies)
            adapter = MoviesAdapter(response.results, response.total_pages)
            (adapter as MoviesAdapter).notifyDataSetChanged()
            Log.e("Main", response.page.toString() + " " + response.total_pages.toString())
        }
    }


    private fun settingupcall(page : Int){
        val compositeDisposable = CompositeDisposable()
        Log.e("Main SettingCall Inside", page.toString())
        compositeDisposable.add(
            ServiceBuilder.buildService()
                .getSimilarResults(movie_id,api_key, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))
    }
}