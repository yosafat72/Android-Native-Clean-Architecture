package com.example.clean.presentation.view.adapter.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.databinding.ListGenreBinding
import com.example.clean.domain.model.movie.GenresItem

class GenreMovieAdapter(private var items: MutableList<GenresItem>) : RecyclerView.Adapter<GenreMovieAdapter.ViewHolder>(){

    class ViewHolder(val binding: ListGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListGenreBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvGenre.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(item: List<GenresItem>) {
        val currentSize = items.size
        items.addAll(item)
        notifyItemRangeChanged(currentSize, item.size)
    }

}