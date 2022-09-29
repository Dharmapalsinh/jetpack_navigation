package com.dharmapal.jetpack_navigation

import MoviesAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dharmapal.gatetouch_task.Retrofit.RetrofitService
import com.dharmapal.jetpack_navigation.Models.VideoResult
import com.dharmapal.jetpack_navigation.databinding.FragmentHomeBinding
import java.nio.file.Files.delete

class homeFragment : Fragment() {

    private lateinit var viewmodel: MainViewmodel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var binding:FragmentHomeBinding
    private val adapter=MoviesAdapter(onClick = {
        findNavController().navigate(homeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it.title!!,it.title))
    }, onwatchclick = {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.link)))})

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater)
        val viewModelFactory=MainViewmodelFactory(Repo(retrofitService))
        viewmodel= ViewModelProvider(this,viewModelFactory)[MainViewmodel::class.java]

        viewmodel.getmovies()
        viewmodel.movielist.observe(viewLifecycleOwner){
            adapter.data=(it)
            binding.recycleview.adapter=adapter
        }
        return binding.root
    }

}