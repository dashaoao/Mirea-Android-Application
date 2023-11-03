package com.example.mireaapp.app.presentation.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mireaapp.domain.repository.EventRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class EventViewModel(
    private val router: Router,
    private val eventId: Int,
    private val eventRepository: EventRepository
) : ViewModel() {

    val event: EventItem = eventRepository.getEvent(eventId).toUI()

    fun onAddClick() = viewModelScope.launch {
        eventRepository.scheduleEvent(eventId)
    }

    fun onBackClick() {
        router.exit()
    }
}