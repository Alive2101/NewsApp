package com.pavel.newsapp.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.pavel.newsapp.controller.NetworkController
import com.pavel.newsapp.util.isNetworkConnected
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NetworkReceiver : BroadcastReceiver() {

    @Inject
    lateinit var networkController: NetworkController

    override fun onReceive(context: Context?, intent: Intent?) {
        val isNetworkConnected: Boolean = context?.isNetworkConnected() == true
        GlobalScope.launch {
            networkController.isNetworkConnected.emit(isNetworkConnected)
        }
    }
}