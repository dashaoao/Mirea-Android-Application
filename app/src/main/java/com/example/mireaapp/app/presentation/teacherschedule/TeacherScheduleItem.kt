package com.example.mireaapp.app.presentation.teacherschedule

import com.example.mireaapp.domain.model.Lesson
import com.example.mireaapp.domain.model.LessonType
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class TeacherScheduleItem(
    val id: Int,
    val name: String,
    val teacher: String,
    val startTime: String,
    val endTime: String,
    val date: String,
    val type: String,
)

fun Lesson.toTeacherSchedule() = TeacherScheduleItem(
    id = id,
    name = subject.name,
    teacher = subject.teachers.firstOrNull { it.lessonType == type }?.name ?: "",
    startTime = time.toString(),
    endTime = time.plusMinutes(90).toString(),
    date = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
    type = when(type) {
        LessonType.SEMINAR -> "пр"
        LessonType.LECTURE -> "лек"
    },
)