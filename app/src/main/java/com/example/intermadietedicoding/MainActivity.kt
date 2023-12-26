package com.example.intermadietedicoding

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intermadietedicoding.ListStory.ListStory
import com.example.intermadietedicoding.Login.Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs: SharedPreferences = getSharedPreferences(
            "com.example.intermediatedicoding", MODE_PRIVATE
        )

        val token = prefs.getString("TOKEN", null);

        if (token == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, ListStory::class.java)
            startActivity(intent)
        }
    }
}