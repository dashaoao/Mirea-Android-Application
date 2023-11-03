package com.example.mireaapp.app.presentation.teacherschedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mireaapp.databinding.TeacherScheduleCardBinding

class TeacherScheduleAdapter : ListAdapter<TeacherScheduleItem, TeacherScheduleAdapter.ScheduleViewHolder>(DiffCallback) {

    inner class ScheduleViewHolder(private val binding: TeacherScheduleCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lesson: TeacherScheduleItem) {
            with(binding) {
                subjectName.text = lesson.name
                teacher.text = lesson.teacher
                type.text = lesson.type
                startTime.text = lesson.startTime
                endTime.text = lesson.endTime
                date.text = lesson.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = TeacherScheduleCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TeacherScheduleItem>() {
            override fun areItemsTheSame(oldItem: TeacherScheduleItem, newItem: TeacherScheduleItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TeacherScheduleItem, newItem: TeacherScheduleItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}