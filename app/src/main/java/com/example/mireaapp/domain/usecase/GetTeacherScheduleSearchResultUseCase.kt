package com.example.mireaapp.domain.usecase

import com.example.mireaapp.domain.model.Lesson
import com.example.mireaapp.domain.repository.SubjectRepository

class GetTeacherScheduleSearchResultUseCase(
    private val subjectRepository: SubjectRepository
) {
    operator fun invoke(query: String): List<Lesson> {
        return if (query.isEmpty()) emptyList()
        else subjectRepository.getLessonsForSemester().values.flatten().filter { lesson ->
            lesson.subject.teachers.any { teacher -> teacher.name.lowercase().startsWith(query) }
        }
    }
}