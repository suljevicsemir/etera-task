package com.semirsuljevic.api.validation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.api.validation.CreditCardHandler
import com.semirsuljevic.api.validation.config.CardValidationModel
import com.semirsuljevic.ui.api.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val creditCardHandler: CreditCardHandler,
    private val appNavigator: AppNavigator
):ViewModel(){

    private val _screenConfig = mutableStateOf(CardValidationModel())
    val screenConfig get() = _screenConfig.value


    private val _uiState = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState.asStateFlow()
    
    
    
    init {
     collectValidator()   
    }
    
    private fun collectValidator() {
        viewModelScope.launch { 
            launch {
                creditCardHandler.formValid.collectLatest { valid ->
                    println("setting form valid, button enabled to: $valid")
                    _screenConfig.value = _screenConfig.value.copy(buttonEnabled = valid)
                }
            }
            launch {
                
                creditCardHandler.screenConfig.collectLatest { config ->
                    println("setting screen config: ${config.toString()}")
                    _screenConfig.value = config 
                } 
            }
        }
    }
    
    fun submit() {
        if(!_screenConfig.value.buttonEnabled) {
            return
        }
        viewModelScope.launch {
            creditCardHandler.invertScreenLoading()
            delay(4000)
            creditCardHandler.invertScreenLoading()
            _uiState.value = true
        }
    }
    
    fun resetHaptic() {
        _uiState.value = false
    }
    
    fun pop() = appNavigator.pop()
}