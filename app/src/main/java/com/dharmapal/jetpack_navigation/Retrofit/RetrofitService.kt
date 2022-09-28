package com.dharmapal.gatetouch_task.Retrofit


import com.dharmapal.gatetouch_task.MyMovies
import com.dharmapal.jetpack_navigation.Models.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search.json")
    fun getAllMovies(
        @Query("engine") engine: String="youtube",
        @Query("search_query") search_query: String="movies",
        @Query("api_key") api_key: String ="8799215f638cb6eeb1705971457adbae56f25f77e19869cbb078b548a84296cc",
        ) : Call<Movies>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://serpapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}