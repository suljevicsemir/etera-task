package com.semirsuljevic.ui.api.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.semirsuljevic.ui.api.theme.AppShapes

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.White.copy(alpha = 0.2f),
    thickness: Dp = 2.dp
) {
    Box(
        modifier = modifier
            .clip(AppShapes.smallRoundedCorner)
            .width(thickness)
            .fillMaxHeight()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        color
                    )
                )
            )
    )
}