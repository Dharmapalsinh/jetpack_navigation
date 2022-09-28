package com.dharmapal.jetpack_navigation

import MoviesAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dharmapal.gatetouch_task.Retrofit.RetrofitService
import com.dharmapal.gatetouch_task.Retrofit.RetrofitService.Companion.retrofitService
import com.dharmapal.jetpack_navigation.databinding.FragmentHomeBinding

class homeFragment : Fragment() {

    private lateinit var viewmodel: MainViewmodel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var binding:FragmentHomeBinding
    private val adapter=MoviesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater)

        val viewModelFactory=MainViewmodelFactory(Repo(retrofitService))
        viewmodel= ViewModelProvider(this,viewModelFactory)[MainViewmodel::class.java]

        viewmodel.getmovies()
        viewmodel.movielist.observe(viewLifecycleOwner){
            adapter.submitList(it)
            binding.recycleview.adapter=adapter
        }
        return binding.root
    }

}