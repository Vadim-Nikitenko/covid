package ru.kiradev.covid.ui

import android.app.Application
import ru.kiradev.covid.di.AppComponent
import ru.kiradev.covid.di.DaggerAppComponent
import ru.kiradev.covid.di.module.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


}