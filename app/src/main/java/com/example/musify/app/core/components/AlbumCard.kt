package com.example.musify.app.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musify.R
import com.example.musify.ui.theme.MusifyTheme

@Composable
fun AlbumCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.height(190.dp).width(160.dp).background(MaterialTheme.colorScheme.surface, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
    ) {
        Card(
            modifier = Modifier.height(160.dp).fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(R.drawable.hood_poet),
                    contentDescription = stringResource(R.string.playlist_cover),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(x = (-10).dp, y = (-10).dp)
                .size(45.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(27.dp)
                )
                .align(Alignment.BottomEnd)

        ) {
            IconButtonNoText(Icons.Default.PlayArrow, {  }, Modifier.size(36.dp).clip(RoundedCornerShape(18.dp)).align(Alignment.Center).background(
                MaterialTheme.colorScheme.onSurface), color = MaterialTheme.colorScheme.surface)
        }
        Text(
            text = "Hood Poet",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 5.dp)
        )
    }
}


@Composable
@Preview
fun AlbumCardPreview() {
    MusifyTheme {
        AlbumCard()
    }
}