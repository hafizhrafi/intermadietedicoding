package com.example.intermadietedicoding.response

import com.google.gson.annotations.SerializedName

data class LoginResultResponse (

    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("story")
    val story: GettAllStoriesHandler? = null,

    @field:SerializedName("listStory")
    val listStory: List<GettAllStoriesHandler>? = emptyList(),
)