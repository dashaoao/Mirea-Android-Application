package com.example.mireaapp.app.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mireaapp.app.navigation.Screens
import com.example.mireaapp.app.presentation.event.toUI
import com.example.mireaapp.domain.repository.EventRepository
import com.github.terrakok.cicerone.Router

class NewsViewModel(
    private val router: Router,
    private val eventRepository: EventRepository
) : ViewModel() {

    private val _state = MutableLiveData<NewsState>()
    val state: LiveData<NewsState> = _state

    init {
        _state.value = NewsState(data = eventRepository.getAllEvents().map { it.toUI() })
    }

    fun onEventClick(eventId: Int) {
        router.navigateTo(Screens.Event(eventId))
    }

    fun onFilterTagSelected(selectedTag: String?) {
        if (selectedTag != null)
            _state.value = NewsState(
                filter = selectedTag,
                data = eventRepository.getEventsByTag(selectedTag).map { it.toUI() }
            )
    }

    fun onFilterTagClick() {
        _state.value = NewsState(
            filter = null,
            data = eventRepository.getAllEvents().map { it.toUI() }
        )
    }
}