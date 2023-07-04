package com.example.notes.adapter.holder

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.roomDatabase.model.NotesModel
import com.example.notes.ui.EditNotesActivity

class NotesHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
    val tvTitle: TextView = itemView.findViewById(R.id.title)
    val tvDate: TextView = itemView.findViewById(R.id.date)
    val tvDesc: TextView = itemView.findViewById(R.id.desc)
    //val pinButton: ImageButton = itemView.findViewById(R.id.btn_pin)
    val ctx = itemView.context

    fun setData(model: NotesModel) {
        tvTitle.text = model.title
        tvDate.text = model.date
        tvDesc.text = model.description
        tvTitle.rootView.setBackgroundColor(model.color)
        tvTitle.rootView.setOnClickListener {
            val go = Intent(ctx, EditNotesActivity::class.java)
            go.putExtra("data", model)
            ctx.startActivity(go)
        }
    }
}