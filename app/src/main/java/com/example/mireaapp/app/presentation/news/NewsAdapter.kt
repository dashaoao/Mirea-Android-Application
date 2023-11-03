package com.example.mireaapp.app.presentation.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mireaapp.app.presentation.event.EventItem
import com.example.mireaapp.app.presentation.news.tagdialog.TagAdapter
import com.example.mireaapp.databinding.EventCardBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager


class NewsAdapter(
    private val eventClickCallback: EventClickCallback
) : ListAdapter<EventItem, NewsAdapter.NewsViewHolder>(DiffCallback) {

    inner class NewsViewHolder(
        private val context: Context,
        private val binding: EventCardBinding
        ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                eventClickCallback.onClick(getItem(adapterPosition).id)
            }
        }

        fun bind(event: EventItem) {
            with(binding) {
                image.load(event.imageUri)
                title.text = event.title
                date.text = event.date
                tagList.adapter = TagAdapter(event.tags)
                tagList.layoutManager = FlexboxLayoutManager(context).apply { FlexDirection.ROW }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = EventCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<EventItem>() {
            override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}