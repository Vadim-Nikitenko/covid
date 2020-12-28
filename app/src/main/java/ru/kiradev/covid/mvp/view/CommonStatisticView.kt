package ru.kiradev.covid.mvp.view

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.kiradev.covid.mvp.model.entity.CommonStatistic

@AddToEndSingle
interface CommonStatisticView: MvpView {
    fun setCommonStatistic(commonStatistic: CommonStatistic?)
    fun paintLineChart(lineData: LineData)
    fun paintBarChart(barData: BarData)
}