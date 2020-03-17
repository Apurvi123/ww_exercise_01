package com.weightwatchers.ww_exercise_01.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Message(val title: String, @JvmField val image: String?, val filter: String): Parcelable {
    fun getImage(): String = "https://www.weightwatchers.com$image"

    companion object
}