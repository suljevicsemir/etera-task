package com.semirsuljevic.api.validation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semirsuljevic.R
import com.semirsuljevic.api.validation.viewmodel.CardViewModel
import com.semirsuljevic.ui.api.buttons.AppButton
import com.semirsuljevic.ui.api.common.UiText
import com.semirsuljevic.ui.api.screen.AppScreen
import com.semirsuljevic.ui.api.theme.AppSizes


@Composable
fun CardValidationScreen(
    viewModel: CardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val haptic = LocalHapticFeedback.current
    LaunchedEffect(uiState) {
        if (uiState) {
            haptic.performHapticFeedback(HapticFeedbackType.ToggleOn)
            viewModel.resetHaptic()
        }
    }
    val scrollState = rememberScrollState()
    AppScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it).
            fillMaxSize()
                .padding(horizontal = AppSizes.scaffoldHorizontal),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppButton(
                label = UiText.StringResource(R.string.go_back).asString(),
                onClick = viewModel::pop
            )
            Text(
                UiText.StringResource(R.string.card_validation_title).asString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(AppSizes.large))
            Row (
                modifier = Modifier.padding(horizontal = AppSizes.medium)
            ){
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Check Circle",
                )
                Spacer(Modifier.width(AppSizes.small))
                Text(
                    UiText.StringResource(R.string.card_validation_subtitle).asString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.height(AppSizes.large))
            FrostedGlassBox()
            Spacer(Modifier.height(2 * AppSizes.xxxLarge))
            AppButton(
                label = "Click me",
                onClick = viewModel::submit,
                enabled = viewModel.screenConfig.buttonEnabled,
                loading = viewModel.screenConfig.loading,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
    
}