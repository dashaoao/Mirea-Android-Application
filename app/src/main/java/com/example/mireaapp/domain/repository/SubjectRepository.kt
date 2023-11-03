package com.example.mireaapp.domain.repository

import com.example.mireaapp.domain.model.Lesson
import com.example.mireaapp.domain.model.Subject
import java.time.LocalDate

interface SubjectRepository {
    fun getLessonsForDate(date: LocalDate): List<Lesson>
    fun getLessonsForSemester(): Map<LocalDate, List<Lesson>>
    fun getAllSubjects(): List<Subject>
}