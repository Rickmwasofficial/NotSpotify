package com.example.musify.app.onboarding.data

import com.example.musify.R

object PageData {
    private val pageData = listOf<Pages>(
        Pages(
            0,
            R.drawable.page1,
            R.string.title1,
            R.string.body1
        ),
        Pages(
            1,
            R.drawable.page2,
            R.string.title2,
            R.string.body2
        ),
        Pages(
            2,
            R.drawable.page3,
            R.string.title3,
            R.string.body3
        )
    )

    fun getData() = pageData
}