package com.example.mireaapp.app

import android.app.Application
import com.example.mireaapp.app.di.dataModule
import com.example.mireaapp.app.di.domainModule
import com.example.mireaapp.app.di.navigationModule
import com.example.mireaapp.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(domainModule, dataModule, navigationModule, viewModelModule))
        }
    }
}