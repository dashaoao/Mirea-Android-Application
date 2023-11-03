package com.example.mireaapp.app.di

import com.example.mireaapp.app.presentation.event.EventViewModel
import com.example.mireaapp.app.presentation.news.NewsViewModel
import com.example.mireaapp.app.presentation.news.tagdialog.TagDialogViewModel
import com.example.mireaapp.app.presentation.profile.ProfileViewModel
import com.example.mireaapp.app.presentation.schedule.ScheduleViewModel
import com.example.mireaapp.app.presentation.settings.SettingsViewModel
import com.example.mireaapp.app.presentation.subjects.SubjectsViewModel
import com.example.mireaapp.app.presentation.teacherschedule.TeachersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        NewsViewModel(router = get(), eventRepository = get())
    }

    viewModel {
        ScheduleViewModel(router = get(), eventRepository = get(), subjectRepository = get())
    }

    viewModel {
        ProfileViewModel(router = get(), userRepository = get())
    }

    viewModel { parameters ->
        EventViewModel(router = get(), eventId = parameters.get(), eventRepository = get())
    }

    viewModel {
        SubjectsViewModel(router = get(), subjectRepository = get())
    }

    viewModel {
        SettingsViewModel(router = get())
    }

    viewModel {
        TagDialogViewModel(eventRepository = get())
    }

    viewModel {
        TeachersViewModel(router = get(), getTeacherScheduleSearchResultUseCase = get())
    }
}