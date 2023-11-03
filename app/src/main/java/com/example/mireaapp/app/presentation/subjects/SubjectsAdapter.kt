package com.example.mireaapp.app.presentation.subjects

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mireaapp.R
import com.example.mireaapp.databinding.SubjectCardBinding
import com.example.mireaapp.domain.model.ControlForm
import com.example.mireaapp.domain.model.Subject

class SubjectsAdapter : ListAdapter<Subject, SubjectsAdapter.SubjectsViewHolder>(DiffCallback) {

    inner class SubjectsViewHolder(
        private val context: Context,
        private val binding: SubjectCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(subject: Subject) {
            with(binding) {
                subjectName.text = subject.name
                controlForm.text = controlFormToString(subject.controlForm)
                subject.teachers.forEach {  teacher ->
                    linearLayout.addView(TextView(context).apply { text = teacher.name })
                }
            }
        }

        private fun controlFormToString(controlForm: ControlForm): String {
            return when(controlForm) {
                ControlForm.EXAM -> context.getString(R.string.exam)
                ControlForm.TEST -> context.getString(R.string.test)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        val binding = SubjectCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SubjectsViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Subject>() {
            override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
                return oldItem == newItem
            }
        }
    }
}