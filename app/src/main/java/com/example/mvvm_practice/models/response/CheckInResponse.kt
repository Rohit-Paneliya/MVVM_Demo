package com.example.mvvm_practice.models.response


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckInResponse(
    val location_details: String? = "",
    val location_id: String? = "",
    val price_per_min: Double? = 0.0
) : Parcelable