package com.example.notes.ui.bottomNavBar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.databinding.FragmentOtherBinding
import com.example.notes.ui.otherFragment.ArchieveActivity
import com.example.notes.ui.otherFragment.DeleteActivity

class OtherFragment : Fragment() {

    private var otherBinding : FragmentOtherBinding? = null
    private val binding get() = otherBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        otherBinding = FragmentOtherBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.tempatSampah.setOnClickListener {
            val moveIntent = Intent(activity, DeleteActivity::class.java)
            startActivity(moveIntent)
        }

        binding.arsip.setOnClickListener {
            val moveIntent = Intent(activity, ArchieveActivity::class.java)
            startActivity(moveIntent)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        otherBinding = null
    }
}