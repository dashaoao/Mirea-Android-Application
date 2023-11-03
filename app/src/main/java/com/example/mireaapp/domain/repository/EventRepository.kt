package com.example.mireaapp.domain.repository

import com.example.mireaapp.domain.model.Event
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface EventRepository {
    fun getAllEvents(): List<Event>
    fun getEventsByTag(tag: String): List<Event>
    fun getEvent(eventId: Int): Event
    fun getAllTags(): List<String>
    fun getScheduledEventsForDate(date: LocalDate): Flow<List<Event>>
    suspend fun scheduleEvent(eventId: Int)
    suspend fun removeEventFromSchedule(eventId: Int)
}