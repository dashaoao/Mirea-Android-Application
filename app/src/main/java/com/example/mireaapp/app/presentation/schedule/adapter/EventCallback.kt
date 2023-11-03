package com.example.mireaapp.app.presentation.schedule.adapter

interface EventCallback {
    fun onEventClick(eventId: Int)
    fun onRemoveClick(eventId: Int)
}