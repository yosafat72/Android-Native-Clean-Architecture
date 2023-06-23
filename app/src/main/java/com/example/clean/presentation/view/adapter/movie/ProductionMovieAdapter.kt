package com.example.clean.presentation.view.adapter.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.ListProductionCompanyBinding
import com.example.clean.domain.model.movie.ProductionCompaniesItem
import com.example.clean.utils.Urls

class ProductionMovieAdapter(private var items: MutableList<ProductionCompaniesItem>) : RecyclerView.Adapter<ProductionMovieAdapter.ViewHolder>(){

    class ViewHolder(val binding: ListProductionCompanyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListProductionCompanyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvCompany.text = item.name
        Glide.with(holder.itemView)
            .load(Urls.BASE_IMAGE_COMPANY_URL + item.logoPath)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(holder.binding.imgCompany)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(item: List<ProductionCompaniesItem>) {
        val currentSize = items.size
        items.addAll(item)
        notifyItemRangeChanged(currentSize, item.size)
    }

}