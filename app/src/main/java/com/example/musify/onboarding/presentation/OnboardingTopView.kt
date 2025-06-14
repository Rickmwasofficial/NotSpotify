package com.example.musify.onboarding.presentation

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.musify.onboarding.data.PageData
import com.example.musify.ui.theme.MusifyTheme

@Composable
fun OnboardingTopView(icon: Int, title: Int, body: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = stringResource(title),
                modifier = Modifier.size(360.dp)
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
            )
            Text(
                text = stringResource(body),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
            )
        }
    }
}

@Preview
@Composable
fun ShowOnboardingTopView() {
    MusifyTheme {
        val data = PageData.getData()
        OnboardingTopView(data[0].img, data[0].title, data[0].body)
    }
}

@Preview
@Composable
fun ShowOnboardingTopView2() {
    MusifyTheme {
        val data = PageData.getData()
        OnboardingTopView(data[1].img, data[1].title, data[1].body)
    }
}

@Preview
@Composable
fun ShowOnboardingTopView3() {
    MusifyTheme {
        val data = PageData.getData()
        OnboardingTopView(data[2].img, data[2].title, data[2].body)
    }
}