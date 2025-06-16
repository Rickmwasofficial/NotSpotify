package com.example.musify.app.core.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButtonNoText(icon: ImageVector, onClick: () -> Unit, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onSurface) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Go to Home",
            modifier = Modifier,
            tint = color
        )
    }
}