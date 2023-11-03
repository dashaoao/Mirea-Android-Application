package com.example.mireaapp.app.presentation.news.tagdialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mireaapp.databinding.TagLayoutBinding

class TagAdapter(
    private val tagList: List<String>,
    private val tagClickCallback: TagClickCallback? = null,
) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    inner class TagViewHolder(private val binding: TagLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                tagClickCallback?.onClick(tagList[adapterPosition])
            }
        }

        fun bind(tag: String) {
            binding.tagText.text = tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = TagLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TagViewHolder(binding)
    }

    override fun getItemCount(): Int = tagList.size

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tagList[position])
    }
}