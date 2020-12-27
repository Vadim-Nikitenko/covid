package ru.kiradev.covid.mvp.presenter

import moxy.MvpPresenter
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.view.MainView
import ru.kiradev.covid.navigation.Screens
import ru.kiradev.covid.ui.BackButtonListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter(val commonStatistic: CommonStatistic?) : MvpPresenter<MainView>(),
    BackButtonListener {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val commonStatistic = commonStatistic
        router.navigateTo(Screens.CommonStatisticScreen(commonStatistic))
        viewState.setupActionBar()
        viewState.setOnClickForSideMenuItems()
    }


    fun navigateToMainPage(commonStatistic: CommonStatistic) {
        router.replaceScreen(Screens.MainScreen(commonStatistic))
    }

    fun backPressedOnOpenedDrawer() {
        viewState.closeDrawer()
    }

    override fun backPressed(): Boolean {
        router.finishChain()
        return true
    }
}