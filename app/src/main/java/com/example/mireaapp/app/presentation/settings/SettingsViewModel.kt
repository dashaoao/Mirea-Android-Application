package com.example.mireaapp.app.presentation.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

class SettingsViewModel(
    private val router: Router,
) : ViewModel() {

    fun onBackClick() {
        router.exit()
    }

    fun onThemeClick() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}