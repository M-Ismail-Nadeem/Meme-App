package com.example.memesharingapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var memeImageView: ImageView
    lateinit var nextMemeButton: Button
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        memeImageView = findViewById(R.id.memeImage)
        nextMemeButton = findViewById(R.id.nextMeme)
        text = findViewById(R.id.textView)

        loadMeme()

        nextMemeButton.setOnClickListener {
            loadMeme()
        }
    }

    private fun loadMeme() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme/desimemes"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                text.text = response.getString("title")
                val url = response.getString("url")
                Glide.with(this).load(url).into(memeImageView)
            },
            {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}