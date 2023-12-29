package com.example.intermadietedicoding.Upload

import ApiConfig
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import com.bumptech.glide.Glide
import com.example.intermadietedicoding.ListStory.ListStory
import com.example.intermadietedicoding.R
import com.example.intermadietedicoding.databinding.ActivityUploadBinding
import com.example.intermadietedicoding.response.GeneralResponseHandler
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private lateinit var binding: ActivityUploadBinding
var fileImage: File? = null

private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())



class Upload : AppCompatActivity() {
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.ivUploadPhoto.setImageURI(uri)
            fileImage = uriToFile(uri,this@Upload)

        }
    }

    fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile(timeStamp, ".jpg", filesDir)
    }
    fun uriToFile(imageUri: Uri, context: Context): File {
        val myFile = createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
        outputStream.close()
        inputStream.close()
        return myFile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val prefs: SharedPreferences = getSharedPreferences(
            "com.example.intermediatedicoding", MODE_PRIVATE
        )


        binding.btnUpload.setOnClickListener {
            if (fileImage != null){
                var image: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "photo", fileImage!!.name,
                    RequestBody.create(MediaType.parse("image/*"), fileImage)
                )

            val token = prefs.getString("TOKEN", "")
            val client = ApiConfig.getApiService(token!!).postStories(
                photo = image,
                description = binding.description.text.toString(),
                lat = null,
                lon = null
            )
            client.enqueue(object :Callback<GeneralResponseHandler>{
                override fun onResponse(
                    call: Call<GeneralResponseHandler>,
                    response: Response<GeneralResponseHandler>
                ) {
                    if (response.isSuccessful && !response.body()?.error!!){
                        val intent = Intent(this@Upload, ListStory::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@Upload, "error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<GeneralResponseHandler>, t: Throwable) {
                    Toast.makeText(this@Upload, "salah",Toast.LENGTH_SHORT).show()
                }

            })
            }else{
                Toast.makeText(this@Upload, "gambar kosong", Toast.LENGTH_SHORT).show()
            }


        }

        binding.btnGallery.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

    }
}