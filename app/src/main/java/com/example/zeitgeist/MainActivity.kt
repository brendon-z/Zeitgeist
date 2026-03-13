package com.example.zeitgeist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zeitgeist.model.Watch
import com.example.zeitgeist.ui.theme.ZeitgeistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZeitgeistTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserWatchList()
                }
            }
        }
    }
}

@Composable
fun UserWatchList(modifier: Modifier = Modifier) {
    var showAddDialog by remember { mutableStateOf(false) }
    val watchesList = remember { mutableStateListOf<Watch>() }

    Scaffold(
        bottomBar = {
            Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxWidth()) {
                Button(
                    onClick = { showAddDialog = true },
                    modifier = modifier.padding(36.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add item")
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
            if (watchesList.isEmpty()) {
                item {
                    Box(modifier = modifier, contentAlignment = Alignment.Center) {
                        Text(text = "No watches yet. Add some?", modifier = modifier.alpha(0.5f))
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Title") },
            text = {
                Text("Fields")
            },
            confirmButton = {

            },
            dismissButton = {

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZeitgeistTheme {
        UserWatchList()
    }
}