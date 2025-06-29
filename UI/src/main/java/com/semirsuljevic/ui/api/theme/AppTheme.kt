package com.semirsuljevic.ui.api.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import com.semirsuljevic.ui.api.theme.AppColors.DarkColorScheme
import com.semirsuljevic.ui.api.theme.AppColors.LightColorScheme

val buttonGradient = Brush.linearGradient(colors = listOf(Color(0xFF8a31fe), Color(0xFF534efe)))
val LocalButtonGradient = staticCompositionLocalOf<Brush> {
    error("No button gradient provided")
}


@Composable
fun TaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme
    
    CompositionLocalProvider(LocalButtonGradient provides buttonGradient) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppFonts.Typography,
            content = content
        )
    }
}