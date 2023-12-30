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
import com.example.intermadietedicoding.Login.Login
import com.example.intermadietedicoding.MapsActivity
import com.example.intermadietedicoding.R
import com.example.intermadietedicoding.Upload.Upload
import com.example.intermadietedicoding.databinding.ActivityListStory2Binding
import com.example.intermadietedicoding.databinding.ActivityLoginBinding
import com.example.intermadietedicoding.response.GeneralResponseHandler
import com.example.intermadietedicoding.response.GettAllStoriesHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

private lateinit var binding: ActivityListStory2Binding
private lateinit var adapter: StoryAdapter
private var page = 1
var stories: List<GettAllStoriesHandler> = emptyList()

class ListStory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListStory2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        page = 1


        val prefs: SharedPreferences = getSharedPreferences(
            "com.example.intermediatedicoding", MODE_PRIVATE
        )

        binding.btnUpload.setOnClickListener {
            val intent = Intent(this@ListStory, Upload::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            prefs.edit().remove("TOKEN").commit()
            val intent = Intent(this@ListStory, Login::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnMaps.setOnClickListener {
            val intent = Intent(this@ListStory, MapsActivity::class.java)
            intent.putExtra("listLocation",stories as Serializable)
            startActivity(intent)
        }

        val token = prefs.getString("TOKEN", "");
        val client = ApiConfig.getApiService(token!!).getStories(page, 10, 1)

        client.enqueue(object : Callback<GeneralResponseHandler> {
            override fun onResponse(
                call: Call<GeneralResponseHandler>,
                response: Response<GeneralResponseHandler>
            ) {
                if (response.isSuccessful && !response.body()?.error!!) {
                    stories = response.body()!!.listStory!!

                    adapter.listData = stories
                    adapter.notifyDataSetChanged()

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

        binding.rvStories.setOnScrollChangeListener { view, i, i2, i3, i4 ->
            if (!view.canScrollVertically(1)){
                loadMore()
            }
        }


        adapter = StoryAdapter(stories, this)

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvStories.addItemDecoration(itemDecoration)
        binding.rvStories.adapter = adapter
    }

    fun loadMore(){
        val prefs: SharedPreferences = getSharedPreferences(
            "com.example.intermediatedicoding", MODE_PRIVATE
        )
        val token = prefs.getString("TOKEN", "");
        val client = ApiConfig.getApiService(token!!).getStories(page+1, 10, 1)

        client.enqueue(object : Callback<GeneralResponseHandler> {
            override fun onResponse(
                call: Call<GeneralResponseHandler>,
                response: Response<GeneralResponseHandler>
            ) {
                if (response.isSuccessful && !response.body()?.error!!) {
                    stories += response.body()!!.listStory!!

                    adapter.listData += stories
                    adapter.notifyDataSetChanged()
                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<GeneralResponseHandler>, t: Throwable) {
                Toast.makeText(this@ListStory, t.message, Toast.LENGTH_SHORT).show()
            }

        }
        )
    }
}