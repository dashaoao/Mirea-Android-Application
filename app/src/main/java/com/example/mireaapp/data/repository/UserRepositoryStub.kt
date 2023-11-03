package com.example.mireaapp.data.repository

import com.example.mireaapp.domain.model.User
import com.example.mireaapp.domain.repository.UserRepository

class UserRepositoryStub : UserRepository {
    override fun getUser(): User {
        return User(
            0,
            "Крендикова Дарья",
            "krendikova.d.v@edu.mirea.ru",
            null
        )
    }
}