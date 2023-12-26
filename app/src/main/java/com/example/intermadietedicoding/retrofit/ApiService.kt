package com.example.githubuser.data.retrofit

import com.example.intermadietedicoding.response.GeneralResponseHandler
import com.example.intermadietedicoding.response.GettAllStoriesHandler
import com.example.intermadietedicoding.response.LoginResultResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("register")
    @FormUrlEncoded
    fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        ):Call<GeneralResponseHandler>

    @POST("login")
    @FormUrlEncoded
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<GeneralResponseHandler>

    @POST("stories")
    @Multipart
    fun postStories(
        @Part("photo") photo: MultipartBody.Part,
        @Field("description") description: String,
        @Field("lat") lat: Float?,
        @Field("lon") lon: Float?
    ):Call<GeneralResponseHandler>

    @POST("stories/guest")
    fun postStoriesGuest(
        @Part("photo") photo: MultipartBody.Part,
        @Field("description") description: String,
        @Field("lat") lat: Float?,
        @Field("lon") lon: Float?
    ):Call<GeneralResponseHandler>

    @GET("stories")
    fun getStories(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("locarion") location: Boolean?
    ):Call<GeneralResponseHandler>

    @GET("  stories/{id}")
    fun getStoriesId(
        @Path("id") id: String?,
    ):Call<GeneralResponseHandler>

}