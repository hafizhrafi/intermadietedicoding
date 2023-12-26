package com.example.intermadietedicoding.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.intermadietedicoding.DetailStory.DetailStory
import com.example.intermadietedicoding.ListStory.ListStory
import com.example.intermadietedicoding.databinding.ActivityItemRecycleBinding
import com.example.intermadietedicoding.response.GettAllStoriesHandler

class StoryAdapter(var listData: List<GettAllStoriesHandler>,var context: Context):RecyclerView.Adapter<StoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ActivityItemRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val users = listData[position]
        holder.bind(users)
    }

    class MyViewHolder(private val binding: ActivityItemRecycleBinding, var context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: GettAllStoriesHandler) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(user.photoUrl)
                    .into(ivDetailPhoto)
                tvItemName.text = user.name
                tvDetailDescription.text = user.description
                layoutItem.setOnClickListener {
                    val intent = Intent(context, DetailStory::class.java)
                    intent.putExtra("idStory", user.id)
                    context.startActivity(intent)

                }
            }
        }
    }
}