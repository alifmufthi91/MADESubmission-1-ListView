package com.example.madesubmission_1.Utils

import android.util.Log
import com.example.madesubmission_1.Model.Movie
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class MovieAPI {


    val movies: ArrayList<Movie>?
        get() {
            val httpClient = OkHttpClient()

            val httpBuider = HttpUrl.parse(url)!!.newBuilder()

            val request = Request.Builder().url(httpBuider.build()).build()

            var response: Response? = null
            try {
                response = httpClient.newCall(request).execute()
                return parseListMovieFromJSON(response!!.body()!!.string())
            } catch (e: IOException) {
                Log.e(TAG, "error in getting response get request with query string okhttp")
            }

            return null
        }

    fun parseListMovieFromJSON(response: String): ArrayList<Movie> {
        val movies = ArrayList<Movie>()

        try {
            val jsonObj = JSONObject(response)
            val moviesResults = jsonObj.getJSONArray("results")
            for (i in 0 until moviesResults.length()) {
                val parsedResults = moviesResults.getJSONObject(i)

                movies.add(
                    Movie(
                        parsedResults.getString("title"),
                        parsedResults.getString("overview"),
                        parsedResults.getString("poster_path"),
                        parsedResults.getDouble("vote_average"),
                        parsedResults.getString("release_date"),
                        parsedResults.getDouble("popularity")
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return movies
    }

    companion object {

        private val TAG = "Error Api"
        private val url = "https://api.themoviedb.org/4/list/1?api_key=19fcb7dafd82f7ba409f93a3018e1f76"
    }

}