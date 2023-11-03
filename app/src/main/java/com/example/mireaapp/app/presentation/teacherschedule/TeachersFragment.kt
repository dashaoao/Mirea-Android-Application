package com.example.mireaapp.app.presentation.teacherschedule

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mireaapp.databinding.FragmentTeachersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeachersFragment : Fragment() {

    private lateinit var binding: FragmentTeachersBinding
    private val viewModel: TeachersViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTeachersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchField.addTextChangedListener(textChangedListener)
        binding.btnBack.setOnClickListener { viewModel.onBackPressed() }
        val adapter =  TeacherScheduleAdapter()
        binding.teacherList.adapter = adapter
        viewModel.schedule.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private val textChangedListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun afterTextChanged(s: Editable?) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
            viewModel.performSearch(s.toString())
    }
}