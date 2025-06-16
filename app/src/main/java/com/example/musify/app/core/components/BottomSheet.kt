package com.example.musify.app.core.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musify.ui.theme.MusifyTheme
import com.example.musify.R

@Composable
fun BottomSheet(title: String, artist: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        SongCard(title, artist, {}, isExpanded = false, modifier = Modifier.padding(horizontal = 10.dp).background(MaterialTheme.colorScheme.surface))
        HorizontalDivider()
        TextWithIcon(icon = R.drawable.play, text = R.string.play_next, onClick = {  })
        TextWithIcon(icon2 = Icons.Default.Add, text = R.string.add_to_queue, onClick = {  }, isImageVector = true)
        TextWithIcon(icon = R.drawable.playlist_added, text = R.string.add_to_playlist, onClick = {  })
        TextWithIcon(icon2 = Icons.Default.FavoriteBorder, text = R.string.add_to_favorites, onClick = {  }, isImageVector = true)
        HorizontalDivider()
        TextWithIcon(icon2 = Icons.Default.Delete, text = R.string.delete, onClick = {  }, isImageVector = true)
    }
}

@Composable
fun TextWithIcon(@StringRes text: Int, onClick: () -> Unit, modifier: Modifier = Modifier, isImageVector: Boolean = false, icon2: ImageVector? = null, @DrawableRes icon: Int? = null) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(horizontal = 15.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = { onClick() }),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isImageVector) {
                Icon(
                    imageVector = icon2!!,
                    contentDescription = stringResource(text)
                )
            } else {
                Icon(
                    painter = painterResource(icon!!),
                    contentDescription = stringResource(text)
                )
            }
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview
@Composable
fun BottomSheetPreview() {
    MusifyTheme {
        BottomSheet("The Come Up", "Polo G")
    }
}