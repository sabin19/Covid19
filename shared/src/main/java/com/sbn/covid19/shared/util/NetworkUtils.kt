package com.sbn.covid19.shared.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

open class NetworkUtils @Inject constructor(val context: Context) {

    open fun hasNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }
}