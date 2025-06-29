package com.semirsuljevic.ui.api.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


object AppColors {
    val DarkColorScheme = darkColorScheme(
        background = Color.Black,
        //text on background
        onBackground = Color.White,
        //text on card background / containers
        onSurface = Color.Gray,
        error = Color.Red
    )

    val LightColorScheme = lightColorScheme(
        background = Color.Black,
        //text on background
        onBackground = Color.White,
        //text on card background / containers
        onSurface = Color.Gray,
        error = Color.Red
    )
}