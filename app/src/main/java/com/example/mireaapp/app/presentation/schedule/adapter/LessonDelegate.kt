package com.example.mireaapp.app.presentation.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mireaapp.app.presentation.schedule.model.LessonDelegateItem
import com.example.mireaapp.app.presentation.schedule.model.ScheduleLessonItem
import com.example.mireaapp.databinding.ScheduleLessonCardBinding

class LessonDelegate : AdapterDelegate {

    inner class ScheduleViewHolder(private val binding: ScheduleLessonCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lesson: ScheduleLessonItem) {
            with(binding) {
                subjectName.text = lesson.name
                teacher.text = lesson.teacher
                type.text = lesson.type
                startTime.text = lesson.startTime
                endTime.text = lesson.endTime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): ScheduleViewHolder {
        val binding = ScheduleLessonCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as ScheduleViewHolder).bind(item.content() as ScheduleLessonItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is LessonDelegateItem
}