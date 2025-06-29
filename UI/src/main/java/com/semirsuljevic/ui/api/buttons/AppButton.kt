package com.semirsuljevic.ui.api.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.semirsuljevic.ui.api.common.AppLoader
import com.semirsuljevic.ui.api.theme.AppShapes
import com.semirsuljevic.ui.api.theme.AppSizes
import com.semirsuljevic.ui.api.theme.LocalButtonGradient

@Composable
fun AppButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    content: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
) {

    val background = if (enabled) LocalButtonGradient.current else Brush.linearGradient(
        listOf(
            Color.White.copy(0.3f),
            Color.White.copy(0.3f), 
        )
    )
    Box(
        modifier = modifier
            .clip(AppShapes.largeRoundedCorner)
            .background(brush = background)
            //we keep button height forced 
            .height(ButtonConstants.BUTTON_HEIGHT)
            .clickable(onClick = onClick)
            .padding(vertical = AppSizes.medium, horizontal = AppSizes.large),
        contentAlignment = Alignment.Center
    ) {
        if(loading) AppLoader()
        else if(content != null)
            content()
        else 
        Text(
            text = label,
        )
    }
}