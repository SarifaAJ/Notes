package com.example.notes.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes.roomDatabase.dao.NotesDao
import com.example.notes.roomDatabase.model.NotesModel

@Database (entities = [NotesModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao() : NotesDao?
}