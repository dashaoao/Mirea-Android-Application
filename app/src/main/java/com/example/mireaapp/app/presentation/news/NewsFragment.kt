package com.example.mireaapp.app.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mireaapp.app.presentation.news.tagdialog.TagDialogFragment
import com.example.mireaapp.databinding.FragmentNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModel()

    private val eventClickCallback = object : EventClickCallback {
        override fun onClick(eventId: Int) {
            viewModel.onEventClick(eventId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsAdapter = NewsAdapter(eventClickCallback)
        binding.apply {
            newsList.adapter = newsAdapter
            btnFilter.setOnClickListener { showTagDialog() }
            filterTag.tagText.setOnClickListener { viewModel.onFilterTagClick() }
            viewModel.state.observe(viewLifecycleOwner) {
                newsAdapter.submitList(it.data)
                filterTag.tagText.text = it.filter
                filterTag.tagText.isVisible = !it.filter.isNullOrBlank()
            }
        }
    }

    private fun showTagDialog() {
        TagDialogFragment.apply {
            setupListener(childFragmentManager, this@NewsFragment) { selectedTag ->
                viewModel.onFilterTagSelected(selectedTag)
            }
        }.show(childFragmentManager)
    }
}