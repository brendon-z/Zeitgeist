package com.example.zeitgeist.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zeitgeist.ui.viewmodels.WatchListViewModel

class WatchListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return WatchListViewModel(WatchListRepository(context)) as T
    }
}