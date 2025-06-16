package com.example.musify.app.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musify.R
import com.example.musify.app.home.presentation.SongBottomSheet
import com.example.musify.ui.theme.MusifyTheme

@Composable
fun SongCard(title: String, artist: String, onClick: () -> Unit, modifier: Modifier = Modifier, isExpanded: Boolean = true) {
    var showBottomSheet by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .padding(vertical = 2.dp)
            .height(60.dp)
            .widthIn(min = 250.dp)
            .background(MaterialTheme.colorScheme.surface),
        onClick = { onClick() },
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().widthIn(min = 250.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.hood_poet),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                alpha = 1f,
                modifier = Modifier.height(60.dp).width(60.dp).clip(RoundedCornerShape(3.dp)),
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 6.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                )
                Text(
                    text = artist,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                )
            }
            if (isExpanded) {
                Box(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .clickable(onClick = {
                            showBottomSheet = true // Show bottom sheet
                        })
                ) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceContainerLow,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
        }
    }
    if (showBottomSheet) {
        SongBottomSheet(
            title = title,
            artist = artist,
            onDismiss = { showBottomSheet = false }
        )
    }
}

@Preview
@Composable
fun PreviewSongCard() {
    MusifyTheme {
        SongCard("My All", "Polo G", {})
    }
}