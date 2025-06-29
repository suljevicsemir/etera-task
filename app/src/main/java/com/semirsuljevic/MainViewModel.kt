package com.semirsuljevic

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.semirsuljevic.api.onboarding.ui.RouteOnboarding
import com.semirsuljevic.ui.api.navigation.AppNavigator
import com.semirsuljevic.ui.api.navigation.AppRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel(){
    private val _startDestination = mutableStateOf<AppRoute?>(null)
    val startDestination: AppRoute? get() = _startDestination.value
    
    fun setNavController(navController: NavHostController) = navigator.setNavController(navController)
    
    init {
     collectStartDestination()   
    }

    private fun collectStartDestination() {
        viewModelScope.launch {
            _startDestination.value = RouteOnboarding()
        }
    }
}