package com.example.notes.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notes.roomDatabase.converters.Converters
import com.example.notes.roomDatabase.dao.NotesDao
import com.example.notes.roomDatabase.model.NotesModel

@Database (entities = [NotesModel::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao() : NotesDao?
}