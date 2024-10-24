package com.pavel.newsapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsEntity")
class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("content")
    val content: String,
    @ColumnInfo("source")
    val source: String,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("publishedAt")
    val publishedAt: String,
)