package com.e.memeapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import android.content.Intent as Intent1

class MainActivity : AppCompatActivity() {

    private lateinit var memeImageView : ImageView;
    private lateinit var progressBar : ProgressBar;
    private var currentImageUrl : String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memeImageView = findViewById<ImageView>(R.id.memeImageView);
        progressBar = findViewById(R.id.progressBar);
        loadMeme();
    }

    fun loadMeme(){
        progressBar.visibility = View.VISIBLE;
        val queue = Volley.newRequestQueue(this);
        val url = "https://meme-api.herokuapp.com/gimme"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                currentImageUrl = response.getString("url");
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE;
                        return false;
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE;
                        return false;
                    }

                }).into(memeImageView);

            },
            {
                Toast.makeText(this,"something went wrong" , Toast.LENGTH_LONG).show();
            })

        queue.add(jsonObjectRequest);
    }
    fun nextMeme(view: View) {
        loadMeme();
    }
    fun shareMeme(view: View) {
        var intent : Intent1 = Intent1(Intent1.ACTION_SEND);
        intent.type = "text/plain";
        intent.putExtra(android.content.Intent.EXTRA_TEXT,"CheckOut this meme $currentImageUrl");
        val chooser = Intent1.createChooser(intent , "share this meme using..");
        startActivity(chooser);
    }
}