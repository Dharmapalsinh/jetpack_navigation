package com.dharmapal.jetpack_navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dharmapal.jetpack_navigation.Models.MovieResult
import com.dharmapal.jetpack_navigation.Models.Movies
import com.dharmapal.jetpack_navigation.Models.VideoResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class MainViewmodel constructor(private val repository: Repo)  : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movielist=MutableLiveData<List<VideoResult>>()


    fun getmovies(){
        val response=repository.getmovies()
        response.enqueue(object :Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                movielist.postValue(response.body()!!.videoResults!!)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }



}

class MainViewmodelFactory(private val repository: Repo
):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewmodel::class.java)) {
            MainViewmodel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
