package ru.kiradev.covid.ui.resources

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.annotation.StringRes
import ru.kiradev.covid.R
import ru.kiradev.covid.mvp.resources.IResourceProvider

class AndroidResourceProvider(private val context: Context): IResourceProvider {

    override fun getPaintLineChartLabelString(): String {
        return context.resources.getString(R.string.paint_line_chart_label)
    }

    override fun getPaintBarChartLabelString(): String {
        return context.resources.getString(R.string.paint_bar_chart_label)
    }

}