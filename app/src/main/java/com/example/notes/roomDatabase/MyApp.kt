package com.example.notes.roomDatabase

import android.app.Application
import androidx.room.Room

class MyApp : Application() {
    companion object {
        var db : AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java, "catatan"
        ).allowMainThreadQueries().build()
    }
}