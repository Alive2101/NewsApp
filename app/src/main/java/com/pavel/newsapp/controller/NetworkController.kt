package com.pavel.newsapp.controller

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkController @Inject constructor() {

    val isNetworkConnected = MutableStateFlow(true)
}