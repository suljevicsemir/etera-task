package com.semirsuljevic.api.validation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.api.validation.CreditCardHandler
import com.semirsuljevic.api.validation.config.CardValidationModel
import com.semirsuljevic.ui.api.textField.TextFieldConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardValidationViewModel @Inject constructor(
    private val creditCardHandler: CreditCardHandler
): ViewModel(){
    
    private val _numberConfig = mutableStateOf(TextFieldConfig())
    val numberConfig get() = _numberConfig.value

    private val _screenConfig = mutableStateOf(CardValidationModel())
    val screenConfig get() = _screenConfig.value


    private val _holderConfig = mutableStateOf(TextFieldConfig())
    val holderConfig get() = _holderConfig.value

    private val _expirationConfig = mutableStateOf(TextFieldConfig())
    val expirationConfig get() = _expirationConfig.value
    
    private val _cvvConfig = mutableStateOf(TextFieldConfig())
    val cvvConfig get() = _cvvConfig.value
    
    init {
        collectValidator()
    }
    
    
    private fun collectValidator() {
        viewModelScope.launch {
            launch { creditCardHandler.cardNumberConfig.collectLatest { _numberConfig.value = it } }
            launch { creditCardHandler.cardHolderConfig.collectLatest { _holderConfig.value = it } }
            launch { creditCardHandler.expirationDateConfig.collectLatest { _expirationConfig.value = it } }
            launch { creditCardHandler.cardCvvConfig.collectLatest { _cvvConfig.value = it } }
            launch { creditCardHandler.screenConfig.collectLatest { _screenConfig.value = it } }
        }
    }

    fun onNumberChanged(value: TextFieldValue) = creditCardHandler.setCardNumber(value)
    fun onHolderChanged(value: TextFieldValue) = creditCardHandler.setCardHolder(value)
    fun onExpirationChanged(value: TextFieldValue) = creditCardHandler.setExpirationDate(value)
    fun onCvvChanged(value: TextFieldValue) = creditCardHandler.setCVV(value)
}