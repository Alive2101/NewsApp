package com.pavel.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 3,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getNewsDao():NewsDao
}