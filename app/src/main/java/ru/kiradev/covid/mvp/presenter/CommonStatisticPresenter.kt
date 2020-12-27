package ru.kiradev.covid.mvp.presenter

import android.annotation.SuppressLint
import com.github.mikephil.charting.data.*
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.model.entity.PredictionData
import ru.kiradev.covid.mvp.model.repository.ICommonStatisticRepo
import ru.kiradev.covid.mvp.resources.IResourceProvider
import ru.kiradev.covid.mvp.view.CommonStatisticView
import ru.kiradev.covid.ui.BackButtonListener
import ru.terrakok.cicerone.Router
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class CommonStatisticPresenter(private val commonStatistic: CommonStatistic?) :
    MvpPresenter<CommonStatisticView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var commonStatisticRepo: ICommonStatisticRepo
    @Inject lateinit var resources: IResourceProvider
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCommonStatistic(commonStatistic)
        loadLineChartData()
        loadBarChartData()
    }

    private fun loadLineChartData() {
        commonStatisticRepo.getTimelineStatistic().observeOn(uiScheduler).subscribe({
            viewState.paintLineChart(configLineChart(it))
        }, {
            it.printStackTrace()
        }).addTo(compositeDisposable)
    }

    private fun loadBarChartData() {
        commonStatisticRepo.getPredictionRussiaData().observeOn(uiScheduler).subscribe({
            viewState.paintBarChart(configBarChart(it))
        }, {
            it.printStackTrace()
        }).addTo(compositeDisposable)
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        var format = SimpleDateFormat("yyyy-M-dd'T'hh:mm:ss")
        val newDate: Date? = format.parse(date)
        format = SimpleDateFormat("M")
        val resultDate = newDate?.let { format.format(newDate) }
        return resultDate.toString()
    }

    private fun configLineChart(list: List<CommonStatistic>): LineData {
        val lineChartDataList = mutableListOf<Entry>()
        list.map { lineChartDataList.add(Entry(formatDate(it.lastUpdate).toFloat(), it.cases.toFloat())) }
        val dataSet = LineDataSet(lineChartDataList, resources.getPaintLineChartLabelString())
        return LineData(dataSet)
    }

    private fun configBarChart(list: List<PredictionData>): BarData {
        val barChartDataList = mutableListOf<BarEntry>()
        var i = 0f
        list.map { barChartDataList.add(BarEntry(++i, it.cases.toFloat())) }
        val dataSet = BarDataSet(barChartDataList, resources.getPaintBarChartLabelString())
        return BarData(dataSet)
    }

    fun backPressed(): Boolean {
        router.finishChain()
        return true
    }

}