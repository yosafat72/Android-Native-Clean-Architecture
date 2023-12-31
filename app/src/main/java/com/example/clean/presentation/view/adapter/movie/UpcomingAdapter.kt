package com.example.clean.presentation.view.adapter.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.ListItemUpcomingMovieBinding
import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.utils.Urls

class UpcomingAdapter(private var items: MutableList<MovieDetailModel>) : RecyclerView.Adapter<UpcomingAdapter.ViewHolder>(){

    class ViewHolder(val binding: ListItemUpcomingMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemUpcomingMovieBinding.inflate(
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
            .load(Urls.BASE_IMAGE_POSTER_URL + item.backdropPath)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(holder.binding.imgPoster)
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
