package com.semirsuljevic.api.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.R
import com.semirsuljevic.api.onboarding.viewmodel.OnboardingViewModel
import com.semirsuljevic.ui.api.buttons.AppButton
import com.semirsuljevic.ui.api.common.UiText
import com.semirsuljevic.ui.api.screen.AppScreen

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    AppScreen (
        canNavigateBack = false
    ){ 
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){ 
            AppButton(
                label = UiText.StringResource(R.string.onboarding_button).asString(),
                onClick = {
                    viewModel.navigateToCard()
                }
            )
        }
    }
}