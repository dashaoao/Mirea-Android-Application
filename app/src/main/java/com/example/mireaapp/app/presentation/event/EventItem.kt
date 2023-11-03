package com.example.mireaapp.app.presentation.event

import com.example.mireaapp.app.presentation.schedule.model.ScheduleEventItem
import com.example.mireaapp.domain.model.Event
import java.time.format.DateTimeFormatter

data class EventItem(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val tags: List<String>,
    val imageUri: String?,
)

fun Event.toUI() = EventItem(
    id = id,
    title = title,
    description = description,
    date = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
    tags = tags,
    imageUri = imageUri,
)

fun Event.toScheduleItem() = ScheduleEventItem(
    id = id,
    title = title,
    startTime = date.format(DateTimeFormatter.ofPattern("HH:mm")),
)