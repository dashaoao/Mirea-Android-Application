package com.example.mireaapp.domain.model

import java.time.LocalDateTime

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val tags: List<String>,
    val imageUri: String?,
)