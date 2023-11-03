package com.example.mireaapp.app.presentation.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mireaapp.R
import com.example.mireaapp.app.presentation.schedule.adapter.DelegateListAdapter
import com.example.mireaapp.app.presentation.schedule.adapter.EventCallback
import com.example.mireaapp.app.presentation.schedule.adapter.EventDelegate
import com.example.mireaapp.app.presentation.schedule.adapter.LessonDelegate
import com.example.mireaapp.app.presentation.schedule.calendar.CalendarFragment
import com.example.mireaapp.databinding.FragmentScheduleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private val viewModel: ScheduleViewModel by viewModel()

    private val eventCallback = object : EventCallback {
        override fun onEventClick(eventId: Int) = viewModel.onEventClick(eventId)
        override fun onRemoveClick(eventId: Int) = viewModel.onRemoveEventClick(eventId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentScheduleBinding.inflate(layoutInflater)
        val fm: FragmentManager = childFragmentManager
        fm.beginTransaction().replace(R.id.calendarContainer, CalendarFragment()).commit()
        if (savedInstanceState == null) {
            viewModel.loadScheduleForDate(LocalDate.now())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DelegateListAdapter().apply {
            addDelegate(LessonDelegate())
            addDelegate(EventDelegate(eventCallback))
        }
        binding.schedule.adapter = adapter
        viewModel.daySchedule.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            with(it.isEmpty()) {
                binding.imgNoLessons.isVisible = this
                binding.textNoLessons.isVisible = this
            }
        }
    }
}