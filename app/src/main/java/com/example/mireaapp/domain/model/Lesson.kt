package com.example.mireaapp.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Lesson(
    val id: Int,
    val subject: Subject,
    val date: LocalDate,
    val time: LocalTime,
    val type: LessonType
)