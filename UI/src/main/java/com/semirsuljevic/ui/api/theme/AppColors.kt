package com.semirsuljevic.ui.api.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


object AppColors {
    val DarkColorScheme = darkColorScheme(
        background = Color.Black,
        //title / first text
        onBackground = Color(0xffFAFAFA),
        //desc/ second text
        onSurface = Color(0xffBDBDBD),
        onPrimaryContainer = Color(0xffF2F2F2),
        error = Color.Red,
    )

    val LightColorScheme = lightColorScheme(
        background = Color.Black,
        //title / first text
        onBackground = Color(0xffFAFAFA),
        //desc/ second text
        onSurface = Color(0xffBDBDBD),
        onPrimaryContainer = Color(0xffF2F2F2),
        error = Color.Red,
    )
}