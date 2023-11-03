package com.example.mireaapp.domain.model

data class User(
    val id: Int,
    val name: String,
    val mail: String,
    val avatarUri: String?,
)