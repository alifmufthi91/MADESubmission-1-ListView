package com.example.madesubmission_1.Activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.madesubmission_1.Adapter.MovieAdapter
import com.example.madesubmission_1.Model.Movie
import com.example.madesubmission_1.R
import com.example.madesubmission_1.Utils.MovieAPI
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {
    private val listMovie = ArrayList<Movie>()
    private lateinit var adapter: MovieAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var fabAbout: FloatingActionButton

    internal lateinit var parentShimmerLayout: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById(R.id.lvMovies)
        parentShimmerLayout = findViewById(R.id.parentShimmerLayout)
        fab = findViewById(R.id.retryButton)
        fabAbout = findViewById(R.id.about)
        parentShimmerLayout.startShimmerAnimation()
        val asyncGetMovies = getMoviesAsync()
        adapter = MovieAdapter(this)
        listView.adapter = adapter
        asyncGetMovies.execute()
        fab.setOnClickListener {
            getMoviesAsync().execute()
            fab.hide()
        }
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val detailIntent = Intent(this@MainActivity,
                MovieDetailActivity::class.java)
            detailIntent.putExtra("detail",listMovie[position])
            startActivity(detailIntent)
        }
        fabAbout.setOnClickListener {
            val aboutIntent = Intent(this@MainActivity,
                AboutActivity::class.java)
            startActivity(aboutIntent)
        }

    }

    private inner class getMoviesAsync : AsyncTask<String, Void, ArrayList<*>>() {
        internal var data = MovieAPI()
        override fun doInBackground(vararg strings: String): ArrayList<*>? {
            return data.movies
        }

        override fun onPostExecute(arrayList: ArrayList<*>?) {
            super.onPostExecute(arrayList)
            if (arrayList != null) {
                parentShimmerLayout.visibility = View.GONE
                parentShimmerLayout.stopShimmerAnimation()
                listMovie.addAll(arrayList as ArrayList<Movie>)
                listMovie.forEach { (item)-> Log.d("name",item) }
                adapter.movies = listMovie
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Gagal mengambil data", Toast.LENGTH_LONG).show()
                fab.show()
            }

        }
    }
}
