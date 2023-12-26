package com.example.intermadietedicoding.Login

import ApiConfig
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.intermadietedicoding.ListStory.ListStory
import com.example.intermadietedicoding.Register.Register
import com.example.intermadietedicoding.databinding.ActivityLoginBinding
import com.example.intermadietedicoding.response.GeneralResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var binding: ActivityLoginBinding

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            var email = binding.edLoginEmail.text.toString()
            var password = binding.edLoginPassword.text.toString()
            val client = ApiConfig.getApiService().postLogin(email, password)
            client.enqueue(object : Callback<GeneralResponseHandler> {
                override fun onResponse(
                    call: Call<GeneralResponseHandler>,
                    response: Response<GeneralResponseHandler>
                ) {
                    if (response.isSuccessful && !response.body()?.error!!) {
                        val prefs: SharedPreferences = getSharedPreferences(
                            "com.example.intermediatedicoding", MODE_PRIVATE
                        )

                        prefs.edit()
                            .apply {
                                putString("TOKEN", response.body()?.loginResult?.token)
                                apply()
                            }

                        val intent = Intent(this@Login, ListStory::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@Login, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<GeneralResponseHandler>, t: Throwable) {
                    Toast.makeText(this@Login, t.message, Toast.LENGTH_SHORT).show()
                }

            }

            )
        }
    }
}