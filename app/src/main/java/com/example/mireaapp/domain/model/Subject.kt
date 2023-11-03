package com.example.mireaapp.domain.model

data class Subject(
    val id: Int,
    val name: String,
    val controlForm: ControlForm,
    val teachers: List<Teacher>,
)
