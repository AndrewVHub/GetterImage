package com.example.alefimage

import android.app.Application
import com.example.alefimage.di.appModule
import com.example.alefimage.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, viewmodelModule))
        }
    }
}