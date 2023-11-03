package com.example.mireaapp.app.presentation.schedule.model

data class ScheduleEventItem(
    val id: Int,
    val title: String,
    override val startTime: String,
) : ScheduleItem