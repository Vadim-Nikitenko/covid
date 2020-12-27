package ru.kiradev.covid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.kiradev.covid.R
import ru.kiradev.covid.databinding.FragmentCommonStatisticBinding
import ru.kiradev.covid.mvp.model.constants.Constant.Companion.COMMON_STAT_KEY
import ru.kiradev.covid.mvp.model.entity.CommonStatistic
import ru.kiradev.covid.mvp.presenter.CommonStatisticPresenter
import ru.kiradev.covid.mvp.view.CommonStatisticView
import ru.kiradev.covid.ui.App
import ru.kiradev.covid.ui.BackButtonListener

class CommonStatisticFragment: MvpAppCompatFragment(), CommonStatisticView, BackButtonListener {

    companion object {
        fun newInstance(commonStatistic: CommonStatistic?) = CommonStatisticFragment().apply {
            arguments = Bundle().apply {
                putParcelable(COMMON_STAT_KEY, commonStatistic)
            }
        }
    }

    private var binding: FragmentCommonStatisticBinding? = null

    val presenter by moxyPresenter {
        val commonStatistic = arguments?.get(COMMON_STAT_KEY) as CommonStatistic?
        CommonStatisticPresenter(commonStatistic).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCommonStatisticBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setCommonStatistic(commonStatistic: CommonStatistic?) {
        binding?.tbSubtitle?.append(commonStatistic?.lastUpdate)
        binding?.tvCases?.text = commonStatistic?.cases
        binding?.tvDeaths?.text = commonStatistic?.deaths
        binding?.tvRecovered?.text = commonStatistic?.recovered
    }

    override fun paintLineChart(lineData: LineData) {
        binding?.lcTimelineChart?.data = lineData
        binding?.lcTimelineChart?.invalidate()
        binding?.lcTimelineChart?.description?.text = getString(R.string.paint_line_chart_description_lable)
    }

    override fun paintBarChart(barData: BarData) {
        binding?.lcBarchart?.data = barData
        binding?.lcBarchart?.invalidate()
        binding?.lcBarchart?.description?.text = getString(R.string.paint_bar_chart_description_label)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun backPressed(): Boolean = presenter.backPressed()

}