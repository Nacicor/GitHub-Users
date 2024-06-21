package com.example.github_users

import android.app.Application
import com.example.github_users._di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StartKoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StartKoinApplication)
            modules(appModule)
        }
    }
}