package com.example.mireaapp.app.presentation.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mireaapp.R
import com.example.mireaapp.app.presentation.news.NewsFragment
import com.example.mireaapp.app.presentation.profile.ProfileFragment
import com.example.mireaapp.app.presentation.schedule.ScheduleFragment
import com.example.mireaapp.databinding.FragmentBottomNavBinding

class BottomFragment : Fragment() {

    private lateinit var binding: FragmentBottomNavBinding
    private var currentTab = R.id.miSchedule

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBottomNavBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.getInt(CURRENT_TAB_KEY)?.let { currentTab = it }
        setupNavigationBar()
        binding.bottomNavigationView.selectedItemId = currentTab
    }

    private fun setupNavigationBar() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.miEvents -> openTab(EVENTS_TAB)
                R.id.miSchedule -> openTab(SCHEDULE_TAB)
                R.id.miProfile -> openTab(PROFILE_TAB)
            }
            currentTab = item.itemId
            true
        }
    }

    private fun openTab(tab: String) {
        val fm = childFragmentManager
        with(fm.beginTransaction()) {
            fm.fragments.filter { it.isVisible }.forEach {
                hide(it)
            }
            fm.findFragmentByTag(tab)?.let {
                show(it)
            } ?: run {
                when (tab) {
                    EVENTS_TAB -> add(R.id.container, NewsFragment(), tab)
                    SCHEDULE_TAB -> add(R.id.container, ScheduleFragment(), tab)
                    PROFILE_TAB -> add(R.id.container, ProfileFragment(), tab)
                    else -> false
                }
            }
            commitNow()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_TAB_KEY, currentTab)
    }

    companion object {
        private const val CURRENT_TAB_KEY = "CURRENT_TAB_KEY"
        private const val EVENTS_TAB = "EVENTS_FRAGMENT"
        private const val SCHEDULE_TAB = "SCHEDULE_FRAGMENT"
        private const val PROFILE_TAB = "PROFILE_FRAGMENT"
    }
}