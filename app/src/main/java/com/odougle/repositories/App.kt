package com.odougle.repositories

import android.app.Application
import com.odougle.repositories.data.di.DataModule
import com.odougle.repositories.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
    }
}