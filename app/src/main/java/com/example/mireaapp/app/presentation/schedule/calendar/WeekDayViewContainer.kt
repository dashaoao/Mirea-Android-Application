package com.example.mireaapp.app.presentation.schedule.calendar

import android.view.View
import com.example.mireaapp.databinding.CalendarDayLayoutBinding
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer

class WeekDayViewContainer(view: View) : ViewContainer(view) {
    lateinit var day: WeekDay
    val binding = CalendarDayLayoutBinding.bind(view)
}