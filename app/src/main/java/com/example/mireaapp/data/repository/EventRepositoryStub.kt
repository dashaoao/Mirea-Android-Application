package com.example.mireaapp.data.repository

import com.example.mireaapp.data.db.dao.EventDao
import com.example.mireaapp.data.db.entity.EventEntity
import com.example.mireaapp.domain.model.Event
import com.example.mireaapp.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime

class EventRepositoryStub(
    private val eventDao: EventDao,
) : EventRepository {

    override fun getAllEvents(): List<Event> {
        return events
    }

    override fun getEventsByTag(tag: String): List<Event> {
        return events.filter { it.tags.contains(tag) }
    }

    override fun getEvent(eventId: Int): Event {
        return events.find { it.id == eventId }!!
    }

    override fun getAllTags(): List<String> {
        return tags
    }

    override fun getScheduledEventsForDate(date: LocalDate): Flow<List<Event>> {
        return eventDao.getAllEvents().map { events ->
            events.filter { it.date.toLocalDate() == date }.map { it.toDomain() }
        }
    }

    override suspend fun scheduleEvent(eventId: Int) {
        eventDao.saveEvent(events.first { it.id == eventId }.toEntity())
    }

    override suspend fun removeEventFromSchedule(eventId: Int) {
        eventDao.deleteEvent(eventId)
    }

    private val tags = listOf(
        "колледж",
        "студентам",
        "ИПТИП",
        "ИТХТ им. М.В. Ломоносова",
        "достижения Университета",
        "ИКБ",
        "военная подготовка",
        "карьера",
        "праздники",
        "ректор",
    )

    private val events = listOf(
        Event(
            0,
            "Поздравление ректора РТУ МИРЭА с Днём шахтёра",
            "Студенты собрались почтить богатый профессиональный опыт ректора РТУ МИРЭА",
            LocalDateTime.now(),
            listOf(tags[0], tags[3], tags[6]),
            "android.resource://com.example.mashkinacoursework/drawable/kudzh"
        ),
        Event(
            1,
            "Поздравление ректора РТУ МИРЭА с Днём понижения стипендий",
            "Студенты собрались отпраздновать лучший день в этом году",
            LocalDateTime.now().plusDays(2),
            listOf(tags[4], tags[8], tags[9]),
            "android.resource://com.example.mashkinacoursework/drawable/kudzh3"
        ),
        Event(
            2,
            "Поздравление ректора РТУ МИРЭА с Днём ректора РТУ МИРЭА",
            "Ну а что, жалко чтоли?",
            LocalDateTime.now().plusDays(4),
            listOf(tags[2], tags[5], tags[7]),
            "android.resource://com.example.mashkinacoursework/drawable/kudzh2"
        )
    )

    private fun Event.toEntity() = EventEntity(
        id = id,
        title = title,
        description = description,
        date = date,
        tags = tags,
        imageUri = imageUri,
    )

    private fun EventEntity.toDomain() = Event(
        id = id,
        title = title,
        description = description,
        date = date,
        tags = tags,
        imageUri = imageUri,
    )
}