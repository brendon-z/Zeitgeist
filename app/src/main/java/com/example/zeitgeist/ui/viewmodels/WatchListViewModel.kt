package com.example.zeitgeist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeitgeist.data.WatchListRepository
import com.example.zeitgeist.model.Watch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WatchListViewModel(val repository: WatchListRepository) : ViewModel() {
    val watches: StateFlow<List<Watch>> = repository.watches.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _showAddDialog = MutableStateFlow(false)
    private val _watchToRemove = MutableStateFlow<String?>(null)

    val showAddDialog: StateFlow<Boolean> = _showAddDialog.asStateFlow()
    val watchToRemove: StateFlow<String?> = _watchToRemove.asStateFlow()

    fun addWatch(watch: Watch) {
        viewModelScope.launch { repository.addWatch(watch) }
    }

    fun removeWatch(id: String) {
        viewModelScope.launch { repository.removeWatch(id) }
    }

    fun confirmRemove() {
        _watchToRemove.value?.let { removeWatch(it) }
        closeRemoveDialog()
    }

    fun openAddDialog() {
        _showAddDialog.value = true
    }

    fun closeAddDialog() {
        _showAddDialog.value = false
    }

    fun openRemoveDialog(id: String) {
        _watchToRemove.value = id
    }

    fun closeRemoveDialog() {
        _watchToRemove.value = null
    }
}