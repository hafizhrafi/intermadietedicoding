package com.example.intermadietedicoding.Register

import ApiConfig
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.intermadietedicoding.Login.Login
import com.example.intermadietedicoding.R
import com.example.intermadietedicoding.databinding.ActivityRegisterBinding
import com.example.intermadietedicoding.response.GeneralResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var binding: ActivityRegisterBinding

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnRegister.setOnClickListener {
            var name = binding.edRegisterName.text.toString()
            var email = binding.edRegisterEmail.editText?.text.toString()
            var password = binding.edRegisterPassword.editText?.text.toString()
            val client = ApiConfig.getApiService().postRegister(name,email,password)

            client.enqueue(object : Callback<GeneralResponseHandler>{
                override fun onResponse(
                    call: Call<GeneralResponseHandler>,
                    response: Response<GeneralResponseHandler>
                ) {
                    if (response.isSuccessful && !response.body()?.error!!){
                        val intent = Intent(this@Register, Login::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@Register, response.message(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<GeneralResponseHandler>, t: Throwable) {
                    Toast.makeText(this@Register, t.message, Toast.LENGTH_SHORT).show()
                }

            }

            )
        }

    }
}