package com.example.mireaapp.app.presentation.news

import com.example.mireaapp.app.presentation.event.EventItem

data class NewsState(
    val filter: String? = null,
    val data: List<EventItem> = listOf()
)