package com.semirsuljevic.api.validation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.api.validation.viewmodel.CardValidationViewModel
import com.semirsuljevic.ui.api.common.AppDivider
import com.semirsuljevic.ui.api.textField.AppTextField
import com.semirsuljevic.ui.api.theme.AppShapes
import com.semirsuljevic.ui.api.theme.AppSizes

@Composable
fun FrostedGlassBox(
    viewModel: CardValidationViewModel = hiltViewModel()
) {
   
    val gradient = Brush.linearGradient(
        0f to MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        
        1f to Color.Transparent,

    )
  
    val gradientBrush = Brush.linearGradient(
        0f to MaterialTheme.colorScheme.onSurface,
        0.2f to Color.Transparent,
        0.8f to Color.Transparent,
        1f to MaterialTheme.colorScheme.onSurface,
    )

    Column(
        modifier = Modifier
            .height(intrinsicSize = IntrinsicSize.Min)
            .clip(AppShapes.largeRoundedCorner)
            .border(0.8.dp, gradientBrush, AppShapes.largeRoundedCorner)
            .background(
                brush = gradient,
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
                    enabled = !viewModel.screenConfig.loading,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                    )
                )
            }
            AppDivider()
            Box(
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                AppTextField(
                    config = viewModel.cvvConfig,
                    onValueChange = viewModel::onCvvChanged,
                    modifier = Modifier.width(IntrinsicSize.Min)
                        .widthIn(max = 120.dp),
                    enabled = !viewModel.screenConfig.loading,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                    )

                )
            }
        }
    }
}