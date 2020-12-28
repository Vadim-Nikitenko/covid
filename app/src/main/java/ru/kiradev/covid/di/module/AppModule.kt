package ru.kiradev.covid.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.kiradev.covid.mvp.resources.IResourceProvider
import ru.kiradev.covid.ui.App
import ru.kiradev.covid.ui.resources.AndroidResourceProvider
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun app(): App {
        return app
    }

    @Singleton
    @Provides
    fun getResources(app: App): IResourceProvider = AndroidResourceProvider(app)

}