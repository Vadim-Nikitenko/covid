package ru.kiradev.covid.di.module

import dagger.Module
import dagger.Provides
import ru.kiradev.covid.mvp.model.api.IDataSource
import ru.kiradev.covid.mvp.model.repository.ICommonStatisticRepo
import ru.kiradev.covid.mvp.model.repository.RetrofitCommonStatisticRepo
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class RepoModule {

    @Provides
    fun commonStatisticRepo(api: IDataSource): ICommonStatisticRepo = RetrofitCommonStatisticRepo(api)

}