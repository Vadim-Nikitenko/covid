package ru.kiradev.covid.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.model.entity.PredictionData

interface IDataSource {

    @GET("status/ru")
    fun getRussiaData(): Single<CommonStatistic>

    @GET("timeline/ru")
    fun getTimelineRussiaData(): Single<List<CommonStatistic>>

    @GET("prediction/ru")
    fun getPredictionRussiaData(): Single<List<PredictionData>>

}