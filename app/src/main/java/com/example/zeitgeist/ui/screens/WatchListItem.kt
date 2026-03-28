package com.example.zeitgeist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.zeitgeist.R
import com.example.zeitgeist.model.Watch
import com.example.zeitgeist.ui.theme.ZeitgeistTheme
import java.io.File

@Composable
fun WatchListItem(
    watch: Watch, modifier: Modifier = Modifier,
    onRemoveClick: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray.copy(alpha = 0.3f))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            val context = LocalContext.current
            AsyncImage(
                model = remember(watch.imagePath) {
                    if (watch.imagePath == Watch.NO_IMAGE) {
                        R.drawable.placeholder_watch
                    } else {
                        File(context.filesDir, watch.imagePath)
                    }
                },
                contentDescription = "${watch.brand} ${watch.modelName}",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp)),
                placeholder = painterResource(R.drawable.placeholder_watch),
                error = painterResource(R.drawable.placeholder_watch)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "${watch.brand} ${watch.modelName}",
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(maxFontSize = 35.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = if (watch.reference != null) "Reference ${watch.reference}" else " ",
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(maxFontSize = 15.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = { onRemoveClick(watch.id) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Icon(Icons.Default.DeleteOutline, contentDescription = "Delete watch")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWatchListItem() {
    ZeitgeistTheme {
        WatchListItem(
            watch = Watch("1", "Alpinist", "Seiko", "123abc"),
            onRemoveClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWatchListItemNoReference() {
    ZeitgeistTheme {
        WatchListItem(
            watch = Watch(
                "1", "Alpinist", "Seiko", null
            ),
            onRemoveClick = {},
        )
    }
}