package com.example.musify.app.core.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonText(@DrawableRes img: Int, @StringRes title: Int, currentRoute: String?, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier.fillMaxSize()
    ) {
        val isSelected = currentRoute?.contains(stringResource(title)) == true
        val color = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            Color.Gray
        }
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                painter = painterResource(img),
                contentDescription = stringResource(title),
                modifier = Modifier.size(24.dp),
                tint = color
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = color
            )
        }
    }
}