package com.example.notes.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.adapter.NotesListAdapter
import com.example.notes.databinding.FragmentNotesListBinding
import com.example.notes.roomDatabase.MyApp.Companion.db
import com.example.notes.roomDatabase.model.NotesModel
import java.time.LocalDate

class NotesListFragment : Fragment() {

    private var notesListBinding: FragmentNotesListBinding? = null
    private val binding get() = notesListBinding!!

    private var listNotes: ArrayList<NotesModel> = ArrayList()
    private lateinit var adapter: NotesListAdapter

    private var selectedDate: LocalDate? = null

    companion object {
        fun newInstance(selectedDate: LocalDate?): NotesListFragment {
            val fragment = NotesListFragment()
            fragment.selectedDate = selectedDate
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notesListBinding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesListAdapter(listNotes)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // database
        fetchNotesByDate()
    }

    fun updateSelectedDate(selectedDate: LocalDate?) {
        this.selectedDate = selectedDate
        fetchNotesByDate()
    }

    private fun fetchNotesByDate() {
        selectedDate?.let { date ->
            listNotes.clear()
            listNotes.addAll(db.notesDao()?.getNotesByDate(date) ?: emptyList())
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        notesListBinding = null
    }

}