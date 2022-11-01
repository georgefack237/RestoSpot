package com.example.restospot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val img = findViewById<ImageView>(R.id.imageView)


        val url = "https://www.google.com/imgres?imgurl=http%3A%2F%2F4.bp.blogspot.com%2F-olJHOtX5sL4%2FUilwYUNMaJI%2FAAAAAAAAACM%2FA_kBuQn5_4A%2Fs1600%2Frestaurants2.jpg&imgrefurl=http%3A%2F%2Fholapsrestaurant.blogspot.com%2F2013%2F09%2Ftantalize-your-taste-bud-at-restaurants.html&tbnid=wLUfpA3TvSJu4M&vet=12ahUKEwijwJj32Iz7AhWkxYUKHepqBsAQMygNegUIARDYAQ..i&docid=z2gX-Q_ee8uthM&w=700&h=514&q=restaurant%20in%20cameroon&ved=2ahUKEwijwJj32Iz7AhWkxYUKHepqBsAQMygNegUIARDYAQ"

          Glide.with(this)
            .load("http://via.placeholder.com/300.png")
            .into(img);


    }
}