package com.semirsuljevic.ui.api.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.semirsuljevic.ui.R

object AppFonts {
    private val fontFamily = FontFamily(
        Font(R.font.spacegrotesk_bold),
        Font(R.font.spacegrotesk_light),
        Font(R.font.spacegrotesk_medium),
        Font(R.font.spacegrotesk_regular, FontWeight.W400),
        Font(R.font.spacegrotesk_semibold, FontWeight.W600)
    )
    val Typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
        ),
        titleLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 35.sp,
        ),
        labelSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        bodySmall = TextStyle(
            fontSize = 11.sp,
            fontFamily = fontFamily
        ),
        
    )
}