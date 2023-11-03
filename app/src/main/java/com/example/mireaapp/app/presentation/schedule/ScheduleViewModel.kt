package com.example.mireaapp.app.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mireaapp.app.navigation.Screens
import com.example.mireaapp.app.presentation.event.toScheduleItem
import com.example.mireaapp.app.presentation.schedule.adapter.DelegateItem
import com.example.mireaapp.app.presentation.schedule.model.EventDelegateItem
import com.example.mireaapp.app.presentation.schedule.model.LessonDelegateItem
import com.example.mireaapp.app.presentation.schedule.model.ScheduleItem
import com.example.mireaapp.app.presentation.schedule.model.toUI
import com.example.mireaapp.domain.model.Event
import com.example.mireaapp.domain.model.Lesson
import com.example.mireaapp.domain.repository.EventRepository
import com.example.mireaapp.domain.repository.SubjectRepository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch
import java.time.LocalDate

class ScheduleViewModel(
    private val router: Router,
    private val subjectRepository: SubjectRepository,
    private val eventRepository: EventRepository,
) : ViewModel() {

    private val _daySchedule: MutableLiveData<List<DelegateItem>> = MutableLiveData<List<DelegateItem>>()
    val daySchedule: LiveData<List<DelegateItem>> = _daySchedule

    val semesterSchedule: Map<LocalDate, List<Lesson>> = subjectRepository.getLessonsForSemester()

    fun loadScheduleForDate(date: LocalDate) = viewModelScope.launch {
        eventRepository.getScheduledEventsForDate(date).collect { events ->
            val lessons = subjectRepository.getLessonsForDate(date)
            val newSchedule = lessons.map { it.toDelegateItem() } + events.map { it.toDelegateItem() }
            _daySchedule.value = newSchedule.sortedBy { (it.content() as ScheduleItem).startTime }
        }
    }

    fun onRemoveEventClick(eventId: Int) {
        viewModelScope.launch {
            eventRepository.removeEventFromSchedule(eventId)
            _daySchedule.value = _daySchedule.value
        }
    }

    fun onEventClick(eventId: Int) {
        router.navigateTo(Screens.Event(eventId))
    }

    private fun Lesson.toDelegateItem() = LessonDelegateItem(
        id = id,
        value = this.toUI()
    )

    private fun Event.toDelegateItem() = EventDelegateItem(
        id = id,
        value = this.toScheduleItem()
    )
}