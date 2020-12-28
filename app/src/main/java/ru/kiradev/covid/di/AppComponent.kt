package ru.kiradev.covid.di

import dagger.Component
import ru.kiradev.covid.di.module.ApiModule
import ru.kiradev.covid.di.module.AppModule
import ru.kiradev.covid.di.module.NavigationModule
import ru.kiradev.covid.di.module.RepoModule
import ru.kiradev.covid.mvp.presenter.CommonStatisticPresenter
import ru.kiradev.covid.mvp.presenter.MainPresenter
import ru.kiradev.covid.mvp.presenter.SplashPresenter
import ru.kiradev.covid.ui.activity.MainActivity
import ru.kiradev.covid.ui.activity.SplashActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        NavigationModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(splashPresenter: SplashPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(commonStatisticPresenter: CommonStatisticPresenter)
}