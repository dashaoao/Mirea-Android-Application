package com.example.mireaapp.app.presentation.subjects

import androidx.lifecycle.ViewModel
import com.example.mireaapp.domain.model.Subject
import com.example.mireaapp.domain.repository.SubjectRepository
import com.github.terrakok.cicerone.Router

class SubjectsViewModel(
    private val router: Router,
    private val subjectRepository: SubjectRepository
) : ViewModel() {

    val subjects: List<Subject> = subjectRepository.getAllSubjects()

    fun onBackClick() {
        router.exit()
    }
}