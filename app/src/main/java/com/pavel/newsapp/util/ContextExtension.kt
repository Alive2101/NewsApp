package com.pavel.newsapp.util

import android.content.Context
import android.net.ConnectivityManager

fun Context.isNetworkConnected(): Boolean {
    return (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.run {
        activeNetworkInfo?.isConnected == true
    } == true
}
