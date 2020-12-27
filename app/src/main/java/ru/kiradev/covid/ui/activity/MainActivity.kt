package ru.kiradev.covid.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.app_bar_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.kiradev.covid.R
import ru.kiradev.covid.databinding.ActivityMainBinding
import ru.kiradev.covid.mvp.model.constants.Constant.Companion.COMMON_STAT_KEY
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.presenter.MainPresenter
import ru.kiradev.covid.mvp.view.MainView
import ru.kiradev.covid.ui.App
import ru.kiradev.covid.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)
    private var binding: ActivityMainBinding? = null
    private var commonStatistic: CommonStatistic? = null

    companion object {
        fun getIntent(context: Context, commonStatistic: CommonStatistic) =
            Intent(context, MainActivity::class.java).apply {
                putExtra(COMMON_STAT_KEY, commonStatistic)
            }
    }

    private val presenter by moxyPresenter {
        commonStatistic = intent.extras?.getParcelable<CommonStatistic>(COMMON_STAT_KEY)
        MainPresenter(commonStatistic).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
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
        if (binding?.drawerLayout?.isOpen == true) {
            presenter.backPressedDrawerOpened()
            return
        }
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun setupActionBar() {
        setSupportActionBar(binding?.include?.toolbar)
        appBarLayout.outlineProvider = null
        val toggle = ActionBarDrawerToggle(
            this,
            binding?.drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
    }

    override fun closeDrawer() {
        binding?.drawerLayout?.close()
    }

    override fun setOnClickForSideMenuItems() {
        binding?.navigationView?.setNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.item_main -> commonStatistic?.let { presenter.navigateToMainPage(it) }
            }
            true
        }
    }

}