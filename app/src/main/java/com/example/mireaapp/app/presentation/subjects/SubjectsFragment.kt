package com.example.mireaapp.app.presentation.subjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mireaapp.databinding.FragmentSubjectsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubjectsFragment : Fragment() {

    private lateinit var binding: FragmentSubjectsBinding
    private val viewModel: SubjectsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSubjectsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SubjectsAdapter()
        binding.subjectList.adapter = adapter
        adapter.submitList(viewModel.subjects)
        binding.btnBack.setOnClickListener {
            viewModel.onBackClick()
        }
    }
}