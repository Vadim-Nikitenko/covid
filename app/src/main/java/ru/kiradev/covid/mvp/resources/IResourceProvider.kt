package ru.kiradev.covid.mvp.resources

interface IResourceProvider {
    fun getPaintLineChartLabelString(): String
    fun getPaintBarChartLabelString(): String
}