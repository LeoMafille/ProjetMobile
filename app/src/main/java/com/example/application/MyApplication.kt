package com.example.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.application.Database.EleveDB


class MyApplication : Application() {
    val database by lazy{
        Room.databaseBuilder(this, EleveDB::class.java, "ElevesDB")
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        private var context: Context? = null
        val appContext: Context?
            get() = context
    }
}