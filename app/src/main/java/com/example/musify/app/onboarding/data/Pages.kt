package com.example.musify.app.onboarding.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Pages(
    val num: Int,
    @DrawableRes val img: Int,
    @StringRes val title: Int,
    @StringRes val body: Int
)