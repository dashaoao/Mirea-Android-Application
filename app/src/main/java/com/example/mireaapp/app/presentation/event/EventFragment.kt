package com.example.mireaapp.app.presentation.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.example.mireaapp.R
import com.example.mireaapp.app.presentation.news.tagdialog.TagAdapter
import com.example.mireaapp.databinding.FragmentEventBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EventFragment : Fragment() {

    private val eventId: Int by lazy { arguments?.getInt(ARG_EVENT_ID) ?: throw IllegalArgumentException() }

    lateinit var binding: FragmentEventBinding
    private val viewModel: EventViewModel by viewModel { parametersOf(eventId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEventBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel.event) {
            binding.image.load(imageUri)
            binding.title.text = title
            binding.date.text = requireContext().getString(R.string.date, date)
            binding.description.text = description
            binding.tagList.adapter = TagAdapter(tags)
            binding.tagList.layoutManager = FlexboxLayoutManager(context).apply { FlexDirection.ROW }
        }
        binding.btnAdd.setOnClickListener {
            viewModel.onAddClick()
            Toast.makeText(requireContext(), R.string.msg_event_scheduled, Toast.LENGTH_SHORT).show()
        }
        binding.btnBack.setOnClickListener { viewModel.onBackClick() }
    }

    companion object {
        private const val ARG_EVENT_ID = "ARG_EVENT_ID"

        fun getInstance(eventId: Int): Fragment {
            val fragment = EventFragment()
            fragment.arguments = bundleOf(ARG_EVENT_ID to eventId)
            return fragment
        }
    }
}