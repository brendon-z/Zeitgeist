package com.example.zeitgeist.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.zeitgeist.model.Watch
import com.example.zeitgeist.ui.theme.ZeitgeistTheme
import java.io.File
import java.util.UUID

@Composable
fun AddWatchDialog(
    onDismissDialog: () -> Unit,
    onConfirmAdd: (Watch) -> Unit,
    context: Context
) {
    var brand by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var reference by remember { mutableStateOf<String?>(null) }
    var imagePath by remember { mutableStateOf(Watch.NO_IMAGE) }

    val pickImage = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val mimeType = context.contentResolver.getType(it)
            val (extension, format) = when (mimeType) {
                "image/png" -> "png" to Bitmap.CompressFormat.PNG
                "image/webp" -> "webp" to Bitmap.CompressFormat.WEBP_LOSSLESS
                else -> "jpg" to Bitmap.CompressFormat.JPEG
            }

            val bitmap = ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(context.contentResolver, it)
            )
            val watchImagesDir = File(context.filesDir, "watchImages").also { dir ->
                if (!dir.exists()) dir.mkdirs()
            }
            val filename = "img_${UUID.randomUUID()}.$extension"
            File(watchImagesDir, filename).outputStream().use { stream ->
                bitmap.compress(format, 100, stream)
            }
            imagePath = "watchImages/$filename"
        }
    }

    AlertDialog(
        onDismissRequest = onDismissDialog,
        title = { Text("Add Watch") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Image picker button that switches to a preview once an image is uploaded
                if (imagePath == Watch.NO_IMAGE) {
                    Button(onClick = { pickImage.launch("image/*") }) {
                        Icon(Icons.Default.AddPhotoAlternate, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Add Photo")
                    }
                } else {
                    Box {
                        AsyncImage(
                            model = File(context.filesDir, imagePath),
                            contentDescription = "Watch image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        IconButton(
                            onClick = { pickImage.launch("image/*") },
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Change photo",
                                tint = Color.White
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = brand,
                    onValueChange = { brand = it },
                    label = { Text("Brand") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = reference ?: "",
                    onValueChange = { reference = it },
                    label = { Text("Reference") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmAdd(
                        Watch(
                            imagePath = imagePath,
                            brand = brand,
                            modelName = name,
                            reference = reference
                        )
                    )
                    onDismissDialog()
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissDialog) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
fun AddWatchDialogPreview() {
    ZeitgeistTheme {
        AddWatchDialog(
            {}, {},
            context = LocalContext.current
        )
    }
}
