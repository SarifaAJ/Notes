package com.example.notes.ui.bottomNavBar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.adapter.NotesListAdapter
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.roomDatabase.MyApp.Companion.db
import com.example.notes.roomDatabase.model.NotesModel
import com.example.notes.ui.AddNotesActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    // database
    private var listNotes: ArrayList<NotesModel> = ArrayList()
    private lateinit var adapter: NotesListAdapter
    //private var db: AppDatabase? = null

    private var isLinearView = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view =  binding.root

        //add note button
        binding.FABAdd.setOnClickListener {
            val moveIntent = Intent(activity, AddNotesActivity::class.java)
            startActivity(moveIntent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // database
        //db = MyApp.db
        listNotes = db?.notesDao()?.getAll() as ArrayList<NotesModel>
        adapter = NotesListAdapter(listNotes)

        // list or grid view
        if (isLinearView) {
            binding.mainRecyData.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.mainRecyData.layoutManager = GridLayoutManager(requireContext(), 2) // Atur jumlah kolom grid di sini
        }
        binding.mainRecyData.adapter = adapter

        // list or grid button
        binding.btnList.setOnClickListener {
            toggleView()
            updateToggleButtonImage()
        }
        adapter.notifyDataSetChanged()
    }

    // database setting
    override fun onResume() {
        super.onResume()

        listNotes.clear()
        listNotes.addAll(db?.notesDao()?.getAll() as Collection<NotesModel>)

        adapter.notifyDataSetChanged()
    }


    // list or grid setting
    private fun updateToggleButtonImage() {
        if (isLinearView) {
            binding.btnList.setImageResource(R.drawable.round_view_list)
        } else {
            binding.btnList.setImageResource(R.drawable.round_grid_view)
        }
    }

    // list or grid setting
    private fun toggleView() {
        isLinearView = !isLinearView

        if (isLinearView) {
            binding.mainRecyData.layoutManager = LinearLayoutManager(requireContext())
        } else {
            binding.mainRecyData.layoutManager = GridLayoutManager(requireContext(), 2) // Atur jumlah kolom grid di sini
        }
        adapter.notifyDataSetChanged()
    }
}