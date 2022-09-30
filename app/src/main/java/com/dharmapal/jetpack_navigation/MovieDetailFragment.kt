package com.dharmapal.jetpack_navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dharmapal.gatetouch_task.Retrofit.RetrofitService
import com.dharmapal.jetpack_navigation.Adapter.ReletedMoviesAdapter
import com.dharmapal.jetpack_navigation.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewmodel: MainViewmodel
    private val retrofitService = RetrofitService.getInstance()
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val adapter= ReletedMoviesAdapter{
        Toast.makeText(context,"caccac",Toast.LENGTH_SHORT).show()
        findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentSelf(it.title!!,it.title))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailFragment_to_homeFragment)
//            findNavController().popBackStack()
        }

        val viewModelFactory=MainViewmodelFactory(Repo(retrofitService))
        viewmodel= ViewModelProvider(this,viewModelFactory)[MainViewmodel::class.java]

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_movieDetailFragment_to_homeFragment)

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        binding.tvTitle.text = args.title
        viewmodel.getmovies()
        viewmodel.movielist.observe(viewLifecycleOwner){

            if (it == null){
                Log.d("tagged","Movielist empty")
            }else{
                val filtered=it.filter {
                    it.title==args.title
                }
                if (filtered.isNotEmpty()) {
                    binding.tvDescription.text = filtered[0].description
                    binding.tvView.text = "${filtered[0].views} Views"
                    binding.tvPbDate.text = filtered[0].publishedDate
                    binding.tvChannelTitle.text = filtered[0].channel!!.name
                    Glide.with(requireContext()).load(filtered[0].channel!!.thumbnail)
                        .into(binding.ivChannelThumb)
                    Glide.with(requireContext()).load(filtered[0].thumbnail!!.static)
                        .into(binding.ivThumbnail)

                    binding.ibPlay.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(filtered[0].link))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }
        }

        viewmodel.reletedMovieList.observe(viewLifecycleOwner){

            Log.d("data", args.title2)

            if (it == null){

                Log.d("tagged","relatedMovielist empty")
            }else{
                adapter.submitList(it)
                val filtered=it.filter {
                    it.title==args.title
                }
                if (filtered.isNotEmpty()) {
                    binding.tvDescription.text = filtered[0].description
                    binding.tvView.text = "${filtered[0].views} Views"
                    binding.tvPbDate.text = filtered[0].publishedDate
                    binding.tvChannelTitle.text = filtered[0].channel!!.name
                    Glide.with(requireContext()).load(filtered[0].channel!!.thumbnail)
                        .into(binding.ivChannelThumb)
                    Glide.with(requireContext()).load(filtered[0].thumbnail!!.static)
                        .into(binding.ivThumbnail)

                    binding.ibPlay.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(filtered[0].link))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }


            binding.rvRelatedMovie.adapter=adapter
        }
        return binding.root
    }



}