package com.dharmapal.jetpack_navigation

import com.dharmapal.gatetouch_task.Retrofit.RetrofitService


class Repo constructor(private val retrofitService: RetrofitService)  {
    fun getmovies()=retrofitService.getAllMovies()

}