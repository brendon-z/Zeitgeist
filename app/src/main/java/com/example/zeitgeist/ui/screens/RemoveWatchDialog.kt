package com.example.zeitgeist.ui.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RemoveWatchDialog(
    onRemoveConfirm: () -> Unit,
    onDismissRemoveDialog: () -> Unit,
) {
    AlertDialog(
        title = { Text("Remove watch?") },
        onDismissRequest = onRemoveConfirm,
        confirmButton = {
            TextButton(onClick = onRemoveConfirm) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRemoveDialog
            ) {
                Text("No")
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewRemoveWatchDialog() {
    RemoveWatchDialog(
        onRemoveConfirm = {},
        onDismissRemoveDialog = {}
    )
}