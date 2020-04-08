package com.sbn.covid19.shared.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "country")
data class CovidCountry(
    @PrimaryKey @SerializedName("country") val country: String,
    @SerializedName("cases") val cases: Int,
    @SerializedName("todayCases") val newCase: Int,
    @SerializedName("deaths") val deaths: Int,
    @SerializedName("todayDeaths") val todayDeaths: Int,
    @SerializedName("active") val active: Int,
    @SerializedName("recovered") val recovered: Int,
    @SerializedName("critical") val critical: Int
) : Parcelable