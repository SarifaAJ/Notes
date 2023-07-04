package com.example.notes.roomDatabase

import android.app.Application
import android.content.Context
import androidx.room.Room

class MyApp : Application() {
    companion object {
        var db : AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "catatan"
        ).allowMainThreadQueries().build()

    }
}
