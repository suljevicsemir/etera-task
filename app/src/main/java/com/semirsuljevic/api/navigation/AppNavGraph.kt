package com.semirsuljevic.api.navigation

import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.semirsuljevic.api.onboarding.ui.OnboardingScreen
import com.semirsuljevic.api.onboarding.ui.RouteOnboarding
import com.semirsuljevic.api.validation.ui.CardValidationScreen
import com.semirsuljevic.api.validation.ui.RouteCardValidation

fun NavGraphBuilder.appNavGraph() {
    composable(route = RouteOnboarding().path) {
        OnboardingScreen()
    }
    composable(route = RouteCardValidation().path) {
        CardValidationScreen()
    }
    
}