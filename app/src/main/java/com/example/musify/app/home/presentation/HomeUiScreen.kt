package com.example.musify.app.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musify.R
import com.example.musify.app.core.components.IconButtonNoText
import com.example.musify.ui.theme.ModakFontFamily
import com.example.musify.ui.theme.MusifyTheme

@Composable
fun HomeUiScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp)
    ) {
        TopNavBar()
    }
}

@Composable
fun TopNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 10.dp),
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

@Preview
@Composable
fun HomeUiScreenPreview() {
    MusifyTheme {
        TopNavBar()
    }
}