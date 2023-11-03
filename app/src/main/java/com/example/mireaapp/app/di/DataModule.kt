package com.example.mireaapp.app.di

import android.app.Application
import androidx.room.Room
import com.example.mireaapp.data.db.AppDatabase
import com.example.mireaapp.data.db.dao.EventDao
import com.example.mireaapp.data.repository.EventRepositoryStub
import com.example.mireaapp.data.repository.SubjectRepositoryStub
import com.example.mireaapp.data.repository.UserRepositoryStub
import com.example.mireaapp.domain.repository.EventRepository
import com.example.mireaapp.domain.repository.SubjectRepository
import com.example.mireaapp.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<EventRepository> {
        EventRepositoryStub(eventDao = get())
    }

    single<SubjectRepository> {
        SubjectRepositoryStub()
    }

    single<UserRepository> {
        UserRepositoryStub()
    }

    single {
        provideDatabase(application = get())
    }

    single<EventDao> {
        provideEventDao(database = get())
    }
}

private fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "app_database")
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideEventDao(database: AppDatabase): EventDao = database.eventDao()