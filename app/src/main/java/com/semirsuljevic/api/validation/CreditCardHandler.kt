package com.semirsuljevic.api.validation

import androidx.compose.ui.text.input.TextFieldValue
import com.semirsuljevic.api.validation.config.CardValidationModel
import com.semirsuljevic.ui.api.textField.TextFieldConfig
import kotlinx.coroutines.flow.Flow

interface CreditCardHandler {
    fun setCardNumber(value: TextFieldValue)
    fun setCardHolder(value: TextFieldValue)
    fun setExpirationDate(value: TextFieldValue)
    fun setCVV(value: TextFieldValue)
    
    fun invertScreenLoading()
    
    val cardNumberConfig: Flow<TextFieldConfig>
    val cardHolderConfig: Flow<TextFieldConfig>
    val expirationDateConfig: Flow<TextFieldConfig>
    val cardCvvConfig: Flow<TextFieldConfig>
    val formValid: Flow<Boolean>
    val screenConfig: Flow<CardValidationModel>
}