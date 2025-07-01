package com.semirsuljevic.ui.api.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 Navigation view model used to implement back pop functionality across the app, 
 injected inside each AppScreen and used inside top bar.
 Usually has more functions to enable other navigation options. That way should be
 injected from the point of usage, not directly inside the AppScreen.
 */
@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigator: AppNavigator
): ViewModel(){
    fun pop() = navigator.pop()
}