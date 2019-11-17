package com.example.madesubmission_1.Activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.madesubmission_1.Model.Movie
import com.example.madesubmission_1.R
import com.google.android.material.appbar.CollapsingToolbarLayout

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var ivDetailGambar : ImageView
    private lateinit var tvTitleMovie: TextView
    private lateinit var tvDeskripsiMovie: TextView
    private lateinit var tvReleaseMovie: TextView
    private lateinit var tvPopularityMovie: TextView
    private lateinit var tvRatingMovie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        val movie = intent.getParcelableExtra("detail") as Movie
        collapsingToolbar.title = movie.name
        collapsingToolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.white))
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.CollapsingToolbarLayoutExpandedTextStyle)
        ivDetailGambar = findViewById(R.id.collapsed_image)
        Glide.with(applicationContext).load("https://image.tmdb.org/t/p/w500_and_h282_bestv2"+movie.photo).into(ivDetailGambar)
        tvTitleMovie = findViewById(R.id.movie_title)
        tvDeskripsiMovie = findViewById(R.id.movie_description)
        tvReleaseMovie = findViewById(R.id.movie_release)
        tvPopularityMovie = findViewById(R.id.movie_popularity)
        tvRatingMovie = findViewById(R.id.movie_rating)
        tvTitleMovie.text = movie.name
        tvDeskripsiMovie.text = movie.description
        tvReleaseMovie.text = movie.releaseDate.toString()
        tvPopularityMovie.text = movie.popularity.toString()
        tvRatingMovie.text = movie.rating.toString()


    }
}
