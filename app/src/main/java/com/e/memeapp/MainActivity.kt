package com.e.memeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var memeImageView : ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memeImageView = findViewById<ImageView>(R.id.memeImageView);
    }

    fun loadMeme(){

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener { response ->
                val url = response.getString(url);
                Glide.with(this).load(url).into(memeImageView);

            },
            Response.ErrorListener {

            })

        queue.add(jsonObjectRequest);
    }
    fun nextMeme(view: View) {}
    fun shareMeme(view: View) {}
}