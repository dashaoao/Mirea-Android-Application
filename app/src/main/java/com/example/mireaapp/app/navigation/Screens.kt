package com.example.mireaapp.app.navigation

import com.example.mireaapp.app.presentation.bottom.BottomFragment
import com.example.mireaapp.app.presentation.event.EventFragment
import com.example.mireaapp.app.presentation.settings.SettingsFragment
import com.example.mireaapp.app.presentation.subjects.SubjectsFragment
import com.example.mireaapp.app.presentation.teacherschedule.TeachersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun BottomNavigation() = FragmentScreen {
        BottomFragment()
    }

    fun Event(eventId: Int) = FragmentScreen {
        EventFragment.getInstance(eventId)
    }

    fun Subjects() = FragmentScreen {
        SubjectsFragment()
    }

    fun Teachers() = FragmentScreen {
        TeachersFragment()
    }

    fun Settings() = FragmentScreen {
        SettingsFragment()
    }
}