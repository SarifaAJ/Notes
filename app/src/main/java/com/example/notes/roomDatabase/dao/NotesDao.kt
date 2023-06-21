package com.example.notes.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.roomDatabase.model.NotesModel

@Dao
interface NotesDao {
    @Query("SELECT * FROM NotesModel ORDER BY id DESC")
    fun getAll() : List<NotesModel?>?
    @Insert
    fun insertNotes(vararg produk: NotesModel)
    @Update
    fun updateNotes(vararg produk: NotesModel)
    @Delete
    fun deleteNotes(vararg produk: NotesModel)
}