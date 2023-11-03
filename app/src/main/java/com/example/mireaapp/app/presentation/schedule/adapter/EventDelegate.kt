package com.example.mireaapp.app.presentation.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mireaapp.app.presentation.schedule.model.EventDelegateItem
import com.example.mireaapp.app.presentation.schedule.model.ScheduleEventItem
import com.example.mireaapp.databinding.ScheduleEventCardBinding

class EventDelegate(
    private val eventCallback: EventCallback
) : AdapterDelegate {

    inner class EventViewHolder(private val binding: ScheduleEventCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: ScheduleEventItem) {
            with(binding) {
                title.text = event.title
                time.text = event.startTime
                btnRemove.setOnClickListener { eventCallback.onRemoveClick(event.id) }
                root.setOnClickListener { eventCallback.onEventClick(event.id) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ScheduleEventCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as EventViewHolder).bind(item.content() as ScheduleEventItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is EventDelegateItem
}