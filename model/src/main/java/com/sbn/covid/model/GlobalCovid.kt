package com.sbn.covid.model


import com.google.gson.annotations.SerializedName

data class GlobalCovid(
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("updated")
    val updated: Long,
    @SerializedName("active")
    val active: Long
){

}