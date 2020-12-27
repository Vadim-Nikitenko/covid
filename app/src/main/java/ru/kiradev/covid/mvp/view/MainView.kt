package ru.kiradev.covid.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView: MvpView {
    fun setupActionBar()
    fun closeDrawer()
    fun setOnClickForSideMenuItems()
}