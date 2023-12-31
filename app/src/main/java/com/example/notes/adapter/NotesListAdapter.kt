package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.adapter.holder.NotesHolder
import com.example.notes.roomDatabase.model.NotesModel

class NotesListAdapter(var data: ArrayList<NotesModel>) : RecyclerView.Adapter<NotesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        return NotesHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_note, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.setData(data[position])
    }
}