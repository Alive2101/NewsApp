package com.pavel.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val content: String,
    val source: String,
    val url: String,
    val author: String,
    val publishedAt: String
): Parcelable