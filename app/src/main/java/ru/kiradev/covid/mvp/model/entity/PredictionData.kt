package ru.kiradev.covid.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PredictionData(
    @Expose val country: String,
    @Expose val date: String,
    @Expose val cases: String
) : Parcelable