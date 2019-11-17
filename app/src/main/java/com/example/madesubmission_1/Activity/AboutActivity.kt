package com.example.madesubmission_1.Activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.madesubmission_1.R

class AboutActivity : AppCompatActivity() {

    private lateinit var imgAbout : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        imgAbout = findViewById(R.id.about_img)
        Glide.with(applicationContext)
            .load("https://www.dicoding.com/images/small/avatar/20190807170701588a85ef9550abc7437223a9093c7295.jpg")
            .into(imgAbout)
    }
}
