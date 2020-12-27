package ru.kiradev.covid.mvp.presenter

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.model.repository.ICommonStatisticRepo
import ru.kiradev.covid.mvp.view.SplashView
import ru.kiradev.covid.ui.BackButtonListener
import ru.terrakok.cicerone.Router
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SplashPresenter : MvpPresenter<SplashView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var commonStatisticRepo: ICommonStatisticRepo
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        commonStatisticRepo.getCommonStatistic().observeOn(uiScheduler).subscribe({
            val commonStatistic = CommonStatistic(
                it.country,
                formatDate(it.lastUpdate),
                formatNumber(it.cases),
                formatNumber(it.deaths),
                formatNumber(it.recovered)
            )
            viewState.onDataLoaded(commonStatistic)
        }, {
            it.printStackTrace()
        }).addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun formatNumber(str: String): String =
        String.format("%,d", str.toInt()).replace(',', ' ')

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String {
        var format = SimpleDateFormat("yyyy-M-dd'T'hh:mm:ss")
        val newDate: Date? = format.parse(date)
        format = SimpleDateFormat("d MMMM hh:mm")
        val resultDate = newDate?.let { format.format(newDate) }
        return resultDate.toString()
    }

    fun backPressed(): Boolean {
        router.finishChain()
        return true
    }
}