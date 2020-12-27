package ru.kiradev.covid.mvp.model.repository

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kiradev.covid.mvp.model.api.IDataSource
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.model.entity.PredictionData

class RetrofitCommonStatisticRepo(private val api: IDataSource) : ICommonStatisticRepo {

    override fun getCommonStatistic(): Single<CommonStatistic> {
        return api.getRussiaData().subscribeOn(Schedulers.io())
    }

    override fun getTimelineStatistic(): Single<List<CommonStatistic>> {
        return api.getTimelineRussiaData().subscribeOn(Schedulers.io())
    }

    override fun getPredictionRussiaData(): Single<List<PredictionData>> {
        return api.getPredictionRussiaData().subscribeOn(Schedulers.io())
    }


}