package com.example.intermadietedicoding.response

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class GeneralResponseHandler (
    @field:SerializedName("error")
    val error: Boolean? = false,

    @field:SerializedName("masagge")
    val massage: String? = null,

    @field:SerializedName("story")
    val story: GettAllStoriesHandler? = null,

    @field:SerializedName("listStory")
    val listStory: List<GettAllStoriesHandler>? = emptyList(),

    @field:SerializedName("loginResult")
    val loginResult: LoginResultResponse? = null

    )