package com.semirsuljevic.api.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.semirsuljevic.api.validation.ui.RouteCardValidation
import com.semirsuljevic.ui.api.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val navigator: AppNavigator
): ViewModel(){
    
    fun navigateToCard() = navigator.navigate(RouteCardValidation())
}