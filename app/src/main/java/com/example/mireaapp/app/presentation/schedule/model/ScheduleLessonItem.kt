package com.example.mireaapp.app.presentation.schedule.model

import com.example.mireaapp.domain.model.Lesson
import com.example.mireaapp.domain.model.LessonType

data class ScheduleLessonItem(
    val id: Int,
    val name: String,
    val teacher: String,
    override val startTime: String,
    val endTime: String,
    val type: String,
) : ScheduleItem

fun Lesson.toUI(): ScheduleLessonItem = ScheduleLessonItem(
    id = id,
    name = subject.name,
    teacher = subject.teachers.firstOrNull { it.lessonType == type }?.name ?: "",
    startTime = time.toString(),
    endTime = time.plusMinutes(90).toString(),
    type = when(type) {
        LessonType.SEMINAR -> "пр"
        LessonType.LECTURE -> "лек"
    },
)