package com.example.mireaapp.app.presentation.news.tagdialog

import androidx.lifecycle.ViewModel
import com.example.mireaapp.domain.repository.EventRepository

class TagDialogViewModel(
    private val eventRepository: EventRepository
): ViewModel() {
    val tagList: List<String> = eventRepository.getAllTags()
}