package com.dharmapal.jetpack_navigation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dharmapal.jetpack_navigation.Models.PeopleAlsoWatched
import com.dharmapal.jetpack_navigation.Models.VideoResult
import com.dharmapal.jetpack_navigation.Models.diff1
import com.dharmapal.jetpack_navigation.databinding.MovieRowBinding

class ReletedMoviesAdapter(val onClick:(PeopleAlsoWatched)->Unit) : ListAdapter<PeopleAlsoWatched, myViewHolder>(diff1) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= MovieRowBinding.inflate(inflater,parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val b1: PeopleAlsoWatched =getItem(position)
        Glide.with(holder.itemView.context).load(b1.thumbnail!!.static).into(holder.binding.ivUserImage)
        holder.binding.tvTitle.text=b1.title

        holder.itemView.setOnClickListener {
            onClick(b1)

        }
    }
}


class myViewHolder(val binding: MovieRowBinding): RecyclerView.ViewHolder(binding.root)