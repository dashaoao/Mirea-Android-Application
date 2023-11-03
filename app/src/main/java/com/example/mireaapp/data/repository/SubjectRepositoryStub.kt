package com.example.mireaapp.data.repository

import com.example.mireaapp.domain.model.ControlForm
import com.example.mireaapp.domain.model.Lesson
import com.example.mireaapp.domain.model.LessonType
import com.example.mireaapp.domain.model.Subject
import com.example.mireaapp.domain.model.Teacher
import com.example.mireaapp.domain.repository.SubjectRepository
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth

class SubjectRepositoryStub : SubjectRepository {

    override fun getLessonsForDate(date: LocalDate): List<Lesson> {
        return generateMonthLessons()[date].orEmpty()
    }

    override fun getLessonsForSemester(): Map<LocalDate, List<Lesson>> {
        return generateMonthLessons()
    }

    override fun getAllSubjects(): List<Subject> {
        return subjects
    }

    private fun generateMonthLessons(): Map<LocalDate, List<Lesson>> = buildMap {
        val currentMonth = YearMonth.now()
        for (day in 1..currentMonth.lengthOfMonth()) {
            val date = currentMonth.atDay(day)
            put(date,  generateLessonsForWeekDay(date, date.dayOfWeek))
        }
    }

    private fun generateLessonsForWeekDay(date: LocalDate, dayOfWeek: DayOfWeek): List<Lesson> = when(dayOfWeek) {
        DayOfWeek.MONDAY -> listOf(
            Lesson(0, subjects[0], date, LocalTime.of(12, 40), LessonType.SEMINAR),
            Lesson(1, subjects[1], date, LocalTime.of(14, 20), LessonType.SEMINAR),
        )
        DayOfWeek.TUESDAY -> listOf(
            Lesson(2, subjects[2], date, LocalTime.of(14, 20), LessonType.SEMINAR),
            Lesson(3, subjects[3], date, LocalTime.of(16, 20), LessonType.LECTURE),
            Lesson(4, subjects[4], date, LocalTime.of(18, 0), LessonType.LECTURE),
        )
        DayOfWeek.WEDNESDAY -> listOf(
            Lesson(5, subjects[5], date, LocalTime.of(12, 40), LessonType.SEMINAR),
            Lesson(9, subjects[0], date, LocalTime.of(14, 20), LessonType.LECTURE),
        )
        DayOfWeek.THURSDAY -> listOf(
            Lesson(6, subjects[1], date, LocalTime.of(18, 0), LessonType.SEMINAR),
        )
        DayOfWeek.FRIDAY -> listOf(
            Lesson(7, subjects[2], date, LocalTime.of(16, 20), LessonType.SEMINAR),
            Lesson(8, subjects[3], date, LocalTime.of(18, 0), LessonType.LECTURE),
        )
        DayOfWeek.SATURDAY -> listOf(
            Lesson(10, subjects[4], date, LocalTime.of(12, 40), LessonType.LECTURE),
            Lesson(11, subjects[5], date, LocalTime.of(14, 20), LessonType.SEMINAR),
        )
        DayOfWeek.SUNDAY -> emptyList()
    }

    private val teachers = listOf(
        Teacher(0, "Дуров П.В.", LessonType.SEMINAR),
        Teacher(1, "Тиньков О.Ю.", LessonType.LECTURE),
        Teacher(2, "Елизавета II", LessonType.SEMINAR),
        Teacher(3, "Дудин М.Д.", LessonType.SEMINAR),
        Teacher(4, "Конюхова Г.П.", LessonType.LECTURE),
        Teacher(5, "Киселев Д.С.", LessonType.LECTURE),
        Teacher(6, "Пыркин Просто Пыркин", LessonType.SEMINAR),
    )

    private val subjects = listOf(
        Subject(0, "Разработка мобильных приложений", ControlForm.EXAM, listOf(teachers[0], teachers[1])),
        Subject(1, "Иностранный язык", ControlForm.EXAM, listOf(teachers[2])),
        Subject(2, "Математический анализ", ControlForm.TEST, listOf(teachers[3])),
        Subject(3, "Теория вероятности и математическая статистика", ControlForm.EXAM, listOf(teachers[4])),
        Subject(4, "Проектирование баз данных", ControlForm.TEST, listOf(teachers[5])),
        Subject(5, "Физика", ControlForm.TEST, listOf(teachers[6])),
    )
}