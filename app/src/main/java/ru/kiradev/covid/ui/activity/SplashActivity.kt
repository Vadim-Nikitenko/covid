package ru.kiradev.covid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.kiradev.covid.R
import ru.kiradev.covid.mvp.model.constants.Constant.Companion.SPLASH_CONTAINER_ID
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.presenter.SplashPresenter
import ru.kiradev.covid.mvp.view.SplashView
import ru.kiradev.covid.navigation.Screens
import ru.kiradev.covid.ui.App
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : MvpAppCompatActivity(), SplashView {

    @Inject lateinit var router: Router
    @Inject lateinit var navigatorHolder: NavigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    companion object {
        fun start(context: Context) = Intent(context, SplashActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    private val presenter by moxyPresenter {
        SplashPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        presenter.backPressed()
    }


}