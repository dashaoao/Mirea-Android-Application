package com.example.mireaapp.app.presentation.schedule.calendar

import android.view.View
import com.example.mireaapp.databinding.CalendarDayLayoutBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view: View) : ViewContainer(view) {
    lateinit var day: CalendarDay
    val binding = CalendarDayLayoutBinding.bind(view)
}