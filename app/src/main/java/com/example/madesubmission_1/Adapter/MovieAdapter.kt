package com.example.madesubmission_1.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.madesubmission_1.Model.Movie
import com.example.madesubmission_1.R

class MovieAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var movies = arrayListOf<Movie>()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var itemView = p1
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, p2, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val hero = getItem(p0) as Movie
        viewHolder.bind(hero)
        return itemView
    }

    override fun getItem(p0: Int): Any {
        return movies[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return movies.size
    }


    private inner class ViewHolder internal constructor(view: View){
        private val txtName: TextView = view.findViewById(R.id.list_item_name)
        private val txtDescription: TextView = view.findViewById(R.id.list_item_status)
        private val imgPhoto: ImageView = view.findViewById(R.id.list_item_photo)

        internal fun bind(hero: Movie){
            txtName.text = hero.name
            val description = hero.description?:""
            txtDescription.text = description.substring(0, Math.min(description.length, 75))+"..."
            Glide.with(context).load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+hero.photo).into(this.imgPhoto)

        }
    }
}