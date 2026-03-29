package com.example.zeitgeist.ui.screens

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zeitgeist.model.Watch
import com.example.zeitgeist.ui.theme.ZeitgeistTheme

@Composable
fun WatchListScreen(
    modifier: Modifier = Modifier,
    watches: List<Watch>,
    showAddDialog: Boolean,
    showRemoveDialog: Boolean,
    watchToEdit: Watch?,
    onAddClick: () -> Unit,
    onRemoveClick: (String) -> Unit,
    onEditClick: (Watch) -> Unit,
    onConfirmAddDialog: (Watch) -> Unit,
    onDismissAddDialog: () -> Unit,
    onConfirmRemoveDialog: () -> Unit,
    onDismissRemoveDialog: () -> Unit,
    onConfirmEditDialog: (Watch) -> Unit,
    onDismissEditDialog: () -> Unit
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
        }, modifier = modifier.padding(10.dp)
    ) { padding ->
        val snapState = rememberLazyListState()
        val flingBehavior = rememberSnapFlingBehavior(lazyListState = snapState)

        LazyRow(
            state = snapState,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (watches.isEmpty()) {
                item {
                    Box(
                        modifier = modifier.fillParentMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No watches yet. Add some?",
                            textAlign = TextAlign.Center,
                            modifier = modifier.alpha(0.5f)
                        )
                    }
                }
            } else {
                items(watches) { watch ->
                    WatchListItem(
                        watch,
                        Modifier.fillParentMaxWidth(),
                        onRemoveClick = onRemoveClick,
                        onEditClick = onEditClick,
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddWatchDialog(
            onDismissDialog = onDismissAddDialog,
            onConfirmAdd = onConfirmAddDialog
        )
    }

    if (showRemoveDialog) {
        RemoveWatchDialog(
            onRemoveConfirm = onConfirmRemoveDialog,
            onDismissRemoveDialog = onDismissRemoveDialog
        )
    }

    watchToEdit?.let {
        EditWatchDialog(
            watch = it,
            onDismissDialog = onDismissEditDialog,
            onConfirmEdit = onConfirmEditDialog
        )
    }

}

@Preview(showBackground = true)
@Composable
fun WatchListPreviewNoWatches() {
    ZeitgeistTheme {
        WatchListScreen(
            watches = emptyList(),
            showAddDialog = false,
            showRemoveDialog = false,
            onAddClick = {},
            onRemoveClick = {},
            onConfirmAddDialog = {},
            onDismissAddDialog = {},
            onDismissRemoveDialog = {},
            onConfirmRemoveDialog = {},
            onEditClick = {},
            onConfirmEditDialog = {},
            onDismissEditDialog = {},
            watchToEdit = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WatchListPreviewWithWatches() {
    ZeitgeistTheme {
        WatchListScreen(
            watches = listOf(
                Watch("1", "Alpinist", "Seiko", "123abc"),
                Watch("2", "Aqua Terra", "Omega", "123abc"),
                Watch("3", "Submariner", "Rolex", "123abc"),
                Watch("4", "Navitimer", "Breitling", "123abc"),
                Watch("5", "Snowflake", "Grand Seiko", "123abc")
            ),
            showAddDialog = false,
            showRemoveDialog = false,
            onAddClick = {},
            onRemoveClick = {},
            onConfirmAddDialog = {},
            onDismissAddDialog = {},
            onDismissRemoveDialog = {},
            onConfirmRemoveDialog = {},
            onEditClick = {},
            onConfirmEditDialog = {},
            onDismissEditDialog = {},
            watchToEdit = null
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
            showRemoveDialog = false,
            onAddClick = {},
            onRemoveClick = {},
            onConfirmAddDialog = {},
            onDismissAddDialog = {},
            onDismissRemoveDialog = {},
            onConfirmRemoveDialog = {},
            onEditClick = {},
            onConfirmEditDialog = {},
            onDismissEditDialog = {},
            watchToEdit = null
        )
    }
}
