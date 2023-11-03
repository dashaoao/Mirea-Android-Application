package com.example.mireaapp.app.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.mireaapp.app.navigation.Screens
import com.example.mireaapp.domain.model.User
import com.example.mireaapp.domain.repository.UserRepository
import com.github.terrakok.cicerone.Router

class ProfileViewModel(
    private val router: Router,
    private val userRepository: UserRepository,
) : ViewModel() {
    val user: User = userRepository.getUser()

    fun onSubjectsClick() {
        router.navigateTo(Screens.Subjects())
    }

    fun onTeachersClick() {
        router.navigateTo(Screens.Teachers())
    }

    fun onSettingsClick() {
        router.navigateTo(Screens.Settings())
    }
}