package com.example.intermadietedicoding.DetailStory

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.intermadietedicoding.ListStory.ListStory
import com.example.intermadietedicoding.R
import com.example.intermadietedicoding.databinding.ActivityDetailStoryBinding
import com.example.intermadietedicoding.databinding.ActivityLoginBinding
import com.example.intermadietedicoding.response.GeneralResponseHandler
import com.example.intermadietedicoding.response.GettAllStoriesHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityDetailStoryBinding


class DetailStory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val idStory = intent.getStringExtra("idStory")
        val storyData: GettAllStoriesHandler? =
            intent.getParcelableExtra("storyData")
        val prefs: SharedPreferences = getSharedPreferences(
            "com.example.intermediatedicoding", MODE_PRIVATE
        )

        if (storyData == null) {
            val token = prefs.getString("TOKEN", "");

            val client = ApiConfig.getApiService(token!!).getStoriesId(id = idStory!!)
            client.enqueue(object : Callback<GeneralResponseHandler> {
                override fun onResponse(
                    call: Call<GeneralResponseHandler>,
                    response: Response<GeneralResponseHandler>
                ) {
                    if (response.isSuccessful && !response.body()?.error!!) {
                        binding.apply {
                            Glide.with(this@DetailStory)
                                .load(response.body()!!.story!!.photoUrl)
                                .into(ivDetail)
                        }
                        binding.tvDetailName.text = response.body()!!.story!!.name
                        binding.tvDetailDescription.text = response.body()!!.story!!.description
                    } else {
                        Toast.makeText(this@DetailStory, response.message(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<GeneralResponseHandler>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }

            )
        } else {
            binding.apply {
                Glide.with(this@DetailStory)
                    .load(storyData.photoUrl)
                    .into(ivDetail)
            }
            binding.tvDetailName.text = storyData.name
            binding.tvDetailDescription.text = storyData.description
        }
    }
}