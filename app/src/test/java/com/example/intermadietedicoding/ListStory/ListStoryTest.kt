package com.example.intermadietedicoding.ListStory

import android.content.ContentValues
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.intermadietedicoding.response.GeneralResponseHandler
import com.example.intermadietedicoding.response.GettAllStoriesHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.AssertionError

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListStoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `when Get Story Should Not Null and Return Data`() = runTest {
        val dummyStoryResponse = DataDummy.generateDummyStoryResponse()
        val client = ApiConfig.getApiService().getStories(1, 10, 1)

        client.enqueue(object : Callback<GeneralResponseHandler> {
            override fun onResponse(
                call: Call<GeneralResponseHandler>,
                response: Response<GeneralResponseHandler>
            ) {
                if (response.isSuccessful && !response.body()?.error!!) {
                    val expectedNews = response.body()!!.listStory!!

                    Assert.assertNotNull(expectedNews)
                    Assert.assertEquals(dummyStoryResponse.listStory, expectedNews)
                    Assert.assertEquals(dummyStoryResponse.listStory?.size, expectedNews)
                    Assert.assertEquals(dummyStoryResponse.listStory?.get(0)?.id, expectedNews)
                } else {
                    Log.d(ContentValues.TAG, "onFailure: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<GeneralResponseHandler>, t: Throwable) {
            }

        }
        )



    }
}