package com.example.clean.presentation.view.adapter.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.ListItemFavoriteBinding
import com.example.clean.domain.model.movie.GenresItem
import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.presentation.view.dashboard.MovieDetailActivity
import com.example.clean.utils.Urls

class FavoriteMovieAdapter(private var items: MutableList<MovieDetailModel>) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.title
        Glide.with(holder.itemView)
            .load(Urls.BASE_IMAGE_NOW_PLAYING_URL + item.posterPath)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(holder.binding.imgMoviePoster)
        if(item.voteAverage == null){
            holder.binding.ratingBar.rating = 0f
        }else{
            holder.binding.ratingBar.rating = item.voteAverage / 2
        }
        holder.binding.tvOverview.text = item.overview

        holder.binding.cardFavorite.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java)
            intent.putExtra("movieId", item.id)
            holder.itemView.context.startActivity(intent)
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