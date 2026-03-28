package com.example.zeitgeist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zeitgeist.data.WatchListViewModelFactory
import com.example.zeitgeist.ui.screens.WatchListScreen
import com.example.zeitgeist.ui.theme.ZeitgeistTheme
import com.example.zeitgeist.ui.viewmodels.WatchListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZeitgeistTheme {
                // Get watch list state
                val watchListViewModel: WatchListViewModel = viewModel(
                    factory = WatchListViewModelFactory(applicationContext)
                )

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WatchListScreen(
                        modifier = Modifier,
                        watches = watchListViewModel.watches.collectAsState().value,
                        showAddDialog = watchListViewModel.showAddDialog.collectAsState().value,
                        showRemoveDialog = watchListViewModel.watchToRemove.collectAsState().value != null,
                        onAddClick = { watchListViewModel.openAddDialog() },
                        onDismissAddDialog = { watchListViewModel.closeAddDialog() },
                        onConfirmAddDialog = watchListViewModel::addWatch,
                        onRemoveClick = watchListViewModel::openRemoveDialog,
                        onConfirmRemoveDialog = watchListViewModel::confirmRemove,
                    )
                }
            }
        }
    }
}
