package ru.kiradev.covid.mvp.model.repository

import io.reactivex.rxjava3.core.Single
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.model.entity.PredictionData

interface ICommonStatisticRepo {

    fun getCommonStatistic(): Single<CommonStatistic>
    fun getTimelineStatistic(): Single<List<CommonStatistic>>
    fun getPredictionRussiaData(): Single<List<PredictionData>>
}