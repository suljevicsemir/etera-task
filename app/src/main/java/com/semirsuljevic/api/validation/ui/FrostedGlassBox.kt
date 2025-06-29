package com.semirsuljevic.api.validation.ui


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer



import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.api.validation.viewmodel.CardValidationViewModel
import com.semirsuljevic.ui.api.textField.AppTextField
import com.semirsuljevic.ui.api.theme.AppShapes
import android.graphics.Shader
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import com.semirsuljevic.ui.api.theme.AppSizes

@Composable
fun FrostedGlassBox(
    viewModel: CardValidationViewModel = hiltViewModel()
) {
    val blurRadius = 20f
    Column(
        modifier = Modifier
            .height(intrinsicSize = IntrinsicSize.Min)
            .clip(AppShapes.largeRoundedCorner)
            
            .graphicsLayer {
                android.graphics.RenderEffect.createBlurEffect(blurRadius, blurRadius, Shader.TileMode.CLAMP)
                
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.25f),
                        Color.White.copy(alpha = 0.05f)
                    )
                ),
                shape = AppShapes.largeRoundedCorner
            )
            .padding(horizontal = AppSizes.medium, vertical = AppSizes.large)
            
    ) {
        AppTextField(
            config = viewModel.numberConfig,
            onValueChange = viewModel::onNumberChanged,
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.screenConfig.loading
        )
        Spacer(Modifier.height(AppSizes.medium))
        AppTextField(
            config = viewModel.holderConfig,
            onValueChange = viewModel::onHolderChanged,
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.screenConfig.loading
        )
        Spacer(Modifier.height(AppSizes.medium))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box (
                modifier = Modifier.width(IntrinsicSize.Min)
            ){
                AppTextField(
                    config = viewModel.expirationConfig,
                    onValueChange = viewModel::onExpirationChanged,
                    modifier = Modifier.width(IntrinsicSize.Min)
                        .widthIn(max = 120.dp),
                    enabled = !viewModel.screenConfig.loading
                )
            }
            VerticalDivider()
            Box(
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                AppTextField(
                    config = viewModel.cvvConfig,
                    onValueChange = viewModel::onCvvChanged,
                    modifier = Modifier.width(IntrinsicSize.Min)
                        .widthIn(max = 120.dp),
                    enabled = !viewModel.screenConfig.loading

                )
            }
        }
    }
}