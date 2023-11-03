package com.example.mireaapp.domain.repository

import com.example.mireaapp.domain.model.User

interface UserRepository {
    fun getUser(): User
}