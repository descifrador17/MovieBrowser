package com.descifrador.moviebrowser.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.descifrador.moviebrowser.R
import com.descifrador.moviebrowser.network.MovieListData
import com.descifrador.moviebrowser.network.ServiceBuilder
import com.descifrador.moviebrowser.util.api_key
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                ServiceBuilder.buildService()
                    .getMovies(api_key,2)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))

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
        progress_bar.visibility = View.GONE
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MoviesAdapter(response.results)
        }
    }
}