package com.example.intermadietedicoding.Upload

import ApiConfig
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import com.bumptech.glide.Glide
import com.example.intermadietedicoding.R
import com.example.intermadietedicoding.databinding.ActivityUploadBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

private lateinit var binding: ActivityUploadBinding
var fileImage: File? = null

class Upload : AppCompatActivity() {
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.ivUploadPhoto.setImageURI(uri)
            fileImage = uri.toFile()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)


        binding.btnUpload.setOnClickListener {
            var image : MultipartBody.Part = MultipartBody.Part.createFormData("photo", fileImage!!.name,
                RequestBody.create(MediaType.parse("image/*"), fileImage))
            val client = ApiConfig.getApiService().postStories(photo = image, description = binding.description,lat = null,lon = null)

        }

        binding.btnGallery.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

    }
}