package com.dharmapal.jetpack_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dharmapal.gatetouch_task.Retrofit.RetrofitService
import com.dharmapal.jetpack_navigation.databinding.FragmentLogInBinding
import com.dharmapal.jetpack_navigation.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()
    private lateinit var viewmodel: MainViewmodel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater)

        val viewModelFactory=MainViewmodelFactory(Repo(retrofitService))
        viewmodel= ViewModelProvider(this,viewModelFactory)[MainViewmodel::class.java]

        binding.tvTitle.setText(args.title)
        viewmodel.getmovies()
        viewmodel.movielist.observe(viewLifecycleOwner){
            val filtered=it.filter {
                it.title==args.title
            }
            binding.tvDescription.text=filtered[0].description
            binding.tvView.text= "${filtered[0].views} Views"
            binding.tvPbDate.text=filtered[0].publishedDate
            Glide.with(requireContext()).load(filtered[0].thumbnail!!.static).into(binding.ivThumbnail)
        }
        return binding.root
    }


}