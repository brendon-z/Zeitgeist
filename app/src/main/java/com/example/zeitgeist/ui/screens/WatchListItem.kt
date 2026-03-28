package com.example.zeitgeist.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zeitgeist.R
import com.example.zeitgeist.model.Watch
import com.example.zeitgeist.ui.theme.ZeitgeistTheme

@Composable
fun WatchListItem(
    watch: Watch, modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray.copy(alpha = 0.3f))
            .padding(30.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_watch),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(30.dp))


            Text(
                text = "${watch.brand} ${watch.modelName}",
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(maxFontSize = 35.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            if (watch.reference != null) {
                Text(text = "Reference ${watch.reference!!}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWatchListItem() {
    ZeitgeistTheme() {
        WatchListItem(watch = Watch("1", "Alpinist", "Seiko", "123abc"))
    }
}