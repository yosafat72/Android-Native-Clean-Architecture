package com.example.clean.presentation.view.adapter.person

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.ListItemActorBinding
import com.example.clean.domain.model.person.PersonDetailModel
import com.example.clean.utils.Urls

class TrendingPersonAdapter(private var items: MutableList<PersonDetailModel>) : RecyclerView.Adapter<TrendingPersonAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemActorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemActorBinding.inflate(
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
            .load(Urls.BASE_IMAGE_TRENDING_URL + item.profilePath)
            .placeholder(R.drawable.img_profile_actor_placeholder)
            .centerCrop()
            .error(R.drawable.img_profile_actor_placeholder)
            .into(holder.binding.imgActor)
        holder.binding.tvActor.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(item: List<PersonDetailModel>) {
        val currentSize = items.size
        items.addAll(item)
        notifyItemRangeChanged(currentSize, item.size)
    }

}