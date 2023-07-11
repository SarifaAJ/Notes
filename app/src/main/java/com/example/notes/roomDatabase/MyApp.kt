package com.example.notes.roomDatabase

import android.app.Application
import androidx.room.Room
import com.example.notes.roomDatabase.dao.NotesDao

class MyApp : Application() {
    companion object {
        lateinit var db: AppDatabase
        lateinit var notesDao: NotesDao
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "catatan"
        ).allowMainThreadQueries().build()
        notesDao = db.notesDao()!!
    }
}
