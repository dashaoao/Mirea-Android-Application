package com.example.mireaapp.app.presentation.teacherschedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mireaapp.domain.usecase.GetTeacherScheduleSearchResultUseCase
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class TeachersViewModel(
    private val router: Router,
    private val getTeacherScheduleSearchResultUseCase: GetTeacherScheduleSearchResultUseCase,
) : ViewModel() {

    private val searchQueryState = MutableSharedFlow<String>()
    private val _schedule = MutableLiveData<List<TeacherScheduleItem>>()
    val schedule: LiveData<List<TeacherScheduleItem>> = _schedule

    init {
        subscribeToSearchQueryState()
    }

    private fun subscribeToSearchQueryState() {
        searchQueryState
            .debounce(500L)
            .distinctUntilChanged()
            .filter { it.isEmpty() || it.isNotBlank() }
            .flatMapLatest { flow { emit(search(it)) } }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private fun search(query: String) {
        _schedule.postValue(getTeacherScheduleSearchResultUseCase(query).map { it.toTeacherSchedule() })
    }

    fun performSearch(text: String?) {
        viewModelScope.launch { text?.let { searchQueryState.emit(text) } }
    }

    fun onBackPressed() {
        router.exit()
    }
}