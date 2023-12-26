package com.example.intermadietedicoding.ListStory

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intermadietedicoding.Adapter.StoryAdapter
import com.example.intermadietedicoding.R
import com.example.intermadietedicoding.Upload.Upload
import com.example.intermadietedicoding.databinding.ActivityListStory2Binding
import com.example.intermadietedicoding.databinding.ActivityLoginBinding
import com.example.intermadietedicoding.response.GeneralResponseHandler
import com.example.intermadietedicoding.response.GettAllStoriesHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityListStory2Binding
private lateinit var adapter: StoryAdapter

class ListStory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStory2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val prefs: SharedPreferences = getSharedPreferences(
            "com.example.intermediatedicoding", MODE_PRIVATE
        )

        binding.btnUpload.setOnClickListener {
            val intent = Intent(this@ListStory , Upload :: class.java)
            startActivity(intent)
        }

        val token = prefs.getString("TOKEN", "");
        var stories: List<GettAllStoriesHandler> = emptyList()
        val client = ApiConfig.getApiService(token!!).getStories(1, 25, true)

        client.enqueue(object : Callback<GeneralResponseHandler> {
            override fun onResponse(
                call: Call<GeneralResponseHandler>,
                response: Response<GeneralResponseHandler>
            ) {
                if (response.isSuccessful && !response.body()?.error!!) {
                    stories = response.body()!!.listStory!!

                    adapter.listData = stories
                    adapter.notifyDataSetChanged()

                    Toast.makeText(this@ListStory, stories.first().name, Toast.LENGTH_SHORT).show()
                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<GeneralResponseHandler>, t: Throwable) {
                Toast.makeText(this@ListStory, t.message, Toast.LENGTH_SHORT).show()
            }

        }
        )

        val layoutManager = LinearLayoutManager(this)
        binding.rvStories.layoutManager = layoutManager

        adapter = StoryAdapter(stories, this)

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvStories.addItemDecoration(itemDecoration)
        binding.rvStories.adapter = adapter
    }
}