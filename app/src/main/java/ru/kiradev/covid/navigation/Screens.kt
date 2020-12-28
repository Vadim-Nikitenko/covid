package ru.kiradev.covid.navigation

import android.content.Context
import android.content.Intent
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.ui.activity.MainActivity
import ru.kiradev.covid.ui.fragment.CommonStatisticFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class CommonStatisticScreen(private val commonStatistic: CommonStatistic?) : SupportAppScreen() {
        override fun getFragment() = CommonStatisticFragment.newInstance(commonStatistic)
    }

    class MainScreen(private val commonStatistic: CommonStatistic) : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent =  MainActivity.getIntent(context, commonStatistic)
    }

}