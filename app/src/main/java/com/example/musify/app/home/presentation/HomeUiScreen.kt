package com.example.musify.app.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musify.R
import com.example.musify.app.core.components.AlbumCard
import com.example.musify.app.core.components.BottomSheet
import com.example.musify.app.core.components.IconButtonNoText
import com.example.musify.app.core.components.PlayingCard
import com.example.musify.app.core.components.SongCard
import com.example.musify.app.core.components.TitleNav
import com.example.musify.ui.theme.ModakFontFamily
import com.example.musify.ui.theme.MusifyTheme

@Composable
fun HomeUiScreen(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            TopNavBar()
            LazyColumn(
                Modifier.fillMaxSize().weight(1f),
            ) {
                item {
                    PlaylistPreviews()
                }
                item {
                    RecentlyPlayed()
                }
                item {
                    MostPlayed()
                }
                item {
                    RecentlyAdded()
                }
            }
            PlayingCard("The Come Up", "Polo G", { })

        }
    }
}

@Composable
fun TopNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                Modifier
                    .size(40.dp)
                    .background(
                        MaterialTheme.colorScheme.inversePrimary,
                        RoundedCornerShape(20.dp)
                    )
                    .border(1.dp, MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.page2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            Text(
                text = stringResource(R.string.not_spotify),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontFamily = ModakFontFamily,
                    fontSize = 20.sp
                ),
                color = Color(0xFF1DB954),
            )
        }
        Box(
            Modifier
                .size(35.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceContainerHighest,
                    RoundedCornerShape(17.dp)
                )
        ) {
            IconButtonNoText(Icons.Default.Search, {  }, Modifier.size(25.dp).align(Alignment.Center))
        }
    }
}

@Composable
fun PlaylistPreviews(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal =  1.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TitleNav("Your Playlists", {}, modifier = Modifier.padding(horizontal = 15.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            repeat(10) {
                item {
                    AlbumCard()
                }
            }
        }
    }
}

@Composable
fun RecentlyPlayed(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal =  1.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TitleNav("Recently Played", {}, modifier = Modifier.padding(horizontal = 15.dp))
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            SongCard("My All", "Polo G", {})
            SongCard("Martin & Gina", "Polo G", {})
            SongCard("The Come Up", "Polo G", {})
            SongCard("Emotional Roller coaster", "Polo G", {})
            SongCard("Dying Breed", "Polo G", {})
        }
    }
}

@Composable
fun RecentlyAdded(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal =  1.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TitleNav("Recently Added", {}, modifier = Modifier.padding(horizontal = 15.dp))
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            SongCard("My All", "Polo G", {})
            SongCard("Martin & Gina", "Polo G", {})
            SongCard("The Come Up", "Polo G", {})
            SongCard("Emotional Roller coaster", "Polo G", {})
            SongCard("Dying Breed", "Polo G", {})
        }
    }
}

@Composable
fun MostPlayed(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal =  1.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TitleNav("Most Played", {}, modifier = Modifier.padding(horizontal = 15.dp))
        LazyHorizontalGrid(
            rows = GridCells.Fixed(5),
            modifier = Modifier.height(300.dp).fillMaxWidth().padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(10) {
                item {
                    SongCard("The Come Up", "Polo G", {})
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongBottomSheet(title: String, artist: String, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        BottomSheet(title, artist)
    }
}

@Preview
@Composable
fun HomeUiScreenPreview() {
    MusifyTheme {
        HomeUiScreen()
    }
}