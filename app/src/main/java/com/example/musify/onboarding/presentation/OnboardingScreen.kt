package com.example.musify.onboarding.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musify.onboarding.data.PageData
import com.example.musify.onboarding.presentation.components.NextButton
import com.example.musify.onboarding.presentation.components.SkipButton
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier,
                     onBoardingViewModel: OnBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
) {
    val data = PageData.getData()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { data.size })
    val coroutineScope = rememberCoroutineScope()
    val buttonState = remember {
        derivedStateOf {
            when(pagerState.currentPage) {
                0 -> listOf("Skip", "Next")
                1 -> listOf("Skip", "Continue")
                2 -> listOf("", "Get Started")
                else -> listOf("", "")
            }
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth()
    ) { page ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            OnboardingTopView(data[page].img, data[page].title, data[page].body, Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp, horizontal = 30.dp)
            ) {
                if (buttonState.value[0].isNotEmpty()) {
                    SkipButton(text = buttonState.value[0], onClick = {
                        val skipPage = pagerState.pageCount-1
                        coroutineScope.launch { pagerState.animateScrollToPage(skipPage) }
                    })
                } else {
                    Spacer(Modifier.width(30.dp))
                }
                NextButton(text = buttonState.value[1], onClick = {
                    if (pagerState.currentPage < 2) {
                        val nextPage = pagerState.currentPage + 1
                        coroutineScope.launch { pagerState.animateScrollToPage(nextPage) }
                    } else {
                        onBoardingViewModel.onEvent(OnBoardingEvent.SaveAppEntry)
                    }
                })
            }
        }
    }
}
