package com.example.clean.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clean.data.model.UserDetailEntity
import com.example.clean.databinding.ListItemUsersBinding

class UserAdapter(private val items: List<UserDetailEntity>) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    class ViewHolder(val binding: ListItemUsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvName.text = item.firstName + " " + item.lastName
        holder.binding.tvEmail.text = item.email
        Glide.with(holder.itemView).load(item.avatar).into(holder.binding.imgAvatar)
    }

    override fun getItemCount(): Int {
        return items.size
    }


}