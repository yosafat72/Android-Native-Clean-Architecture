package com.example.clean.presentation.view.adapter.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.ListItemNowPlayingBinding
import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.utils.Urls

class NowPlayingAdapter(private var items: MutableList<MovieDetailModel>) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>(){

    class ViewHolder(val binding: ListItemNowPlayingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNowPlayingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView)
            .load(Urls.BASE_IMAGE_NOW_PLAYING_URL + item.posterPath)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(holder.binding.imgNowPlaying)
        holder.binding.tvNowPlaying.text = item.title
        if(item.voteAverage == null){
            holder.binding.ratingBar.rating = 0f
        }else{
            holder.binding.ratingBar.rating = item.voteAverage / 2
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(item: List<MovieDetailModel>) {
        val currentSize = items.size
        items.addAll(item)
        notifyItemRangeChanged(currentSize, item.size)
    }

}