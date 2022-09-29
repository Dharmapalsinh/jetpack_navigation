package com.dharmapal.jetpack_navigation

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dharmapal.jetpack_navigation.Models.MovieResult
import com.dharmapal.jetpack_navigation.Models.Movies
import com.dharmapal.jetpack_navigation.Models.PeopleAlsoWatched
import com.dharmapal.jetpack_navigation.Models.VideoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewmodel constructor(private val repository: Repo)  : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movielist=MutableLiveData<List<VideoResult>>()
    val reletedMovieList=MutableLiveData<List<PeopleAlsoWatched>>()
    val usernamelist=MutableLiveData<List<User>>()

    fun getmovies(){
        val response=repository.getmovies()
        response.enqueue(object :Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                movielist.postValue(response.body()!!.videoResults!!)
                reletedMovieList.postValue(response.body()!!.peopleAlsoWatched!!)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun addUserData(username:String, password:String,email:String,phone:Int,context: Context){
        val db= UserDatabase.getDatabase(context)
        val dao=db.dao()
        runBlocking(Dispatchers.IO){
            dao.addUser(User(username = username, password = password, email = email, phone = phone))
        }
    }

    fun getUserdata(context: Context){

        val db= UserDatabase.getDatabase(context)
        val dao=db.dao()

        viewModelScope.launch(Dispatchers.IO) {
            val data=dao.getUsers().toString()
            val data2=dao.getUsers()
            withContext(Dispatchers.Main){
                val list= mutableListOf<User>()
                data2.forEach {
                    list.add(it)
                }
                usernamelist.postValue(list)
                Log.d("uname",data)
            }
        }
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
