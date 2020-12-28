package ru.kiradev.covid.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommonStatistic(
    @Expose val country: String,
    @Expose val lastUpdate: String,
    @Expose val cases: String,
    @Expose val deaths: String,
    @Expose val recovered: String
) : Parcelable
