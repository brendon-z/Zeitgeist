package com.example.zeitgeist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zeitgeist.model.Watch
import com.example.zeitgeist.ui.theme.ZeitgeistTheme

@Composable
fun WatchListScreen(
    modifier: Modifier = Modifier,
    watches: List<Watch>,
    showAddDialog: Boolean,
    onAddClick: () -> Unit,
    onDismissDialog: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxWidth()) {
                Button(
                    onClick = onAddClick,
                    modifier = modifier.padding(36.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add watch")
                }
            }
        },
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (watches.isEmpty()) {
                item {
                    Box(modifier = modifier, contentAlignment = Alignment.Center) {
                        Text(text = "No watches yet. Add some?", modifier = modifier.alpha(0.5f))
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AddWatchDialog(
            onDismissDialog = onDismissDialog
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun WatchListPreviewNoAddDialog() {
    ZeitgeistTheme {
        WatchListScreen(
            watches = emptyList(),
            showAddDialog = false,
            onAddClick = {},
            onDismissDialog = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WatchListPreviewShowAddDialog() {
    ZeitgeistTheme {
        WatchListScreen(
            watches = emptyList(),
            showAddDialog = true,
            onAddClick = {},
            onDismissDialog = {}
        )
    }
}
