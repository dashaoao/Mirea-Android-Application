package com.example.mireaapp.app.di

import com.example.mireaapp.domain.usecase.GetTeacherScheduleSearchResultUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetTeacherScheduleSearchResultUseCase> {
        GetTeacherScheduleSearchResultUseCase(subjectRepository = get())
    }
}