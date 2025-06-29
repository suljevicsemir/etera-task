package com.semirsuljevic.internal.validation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import com.semirsuljevic.R
import com.semirsuljevic.api.validation.CreditCardHandler
import com.semirsuljevic.api.validation.config.CardValidationModel
import com.semirsuljevic.foundation.api.creditCard.CreditCardValidator
import com.semirsuljevic.ui.api.common.UiText
import com.semirsuljevic.ui.api.textField.TextFieldConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class CreditCardHandlerImpl @Inject constructor(
    private val validator: CreditCardValidator
): CreditCardHandler{
    private val _screenConfig = MutableStateFlow(CardValidationModel())
    private val _cardNumberConfig = MutableStateFlow(
        TextFieldConfig(
        hint = UiText.StringResource(R.string.card_validation_number),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )
    )
    private val _cardHolderConfig = MutableStateFlow(
        TextFieldConfig(
            hint = UiText.StringResource(R.string.card_validation_holder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
    )
    private val _expirationDateConfig = MutableStateFlow(
        TextFieldConfig(
            hint = UiText.StringResource(R.string.card_validation_expiry),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
        )
    )
    private val _cardCvvConfig = MutableStateFlow(
        TextFieldConfig(
            hint = UiText.StringResource(R.string.card_validation_cvv),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
        )
    )
    
    private val _formValid = MutableStateFlow(false)

    override fun setCardNumber(value: TextFieldValue) {
        _cardNumberConfig.update { _cardNumberConfig.value.copy(value = formatCardNumber(value), errorText = UiText.StringValue(""))
        }
        validateForm()
    }

    override fun setCardHolder(value: TextFieldValue) {
        _cardHolderConfig.update { _cardHolderConfig.value.copy(value = value, errorText = UiText.StringValue("")) }
        validateForm()
    }

    override fun setExpirationDate(value: TextFieldValue) {
        _expirationDateConfig.update { _expirationDateConfig.value.copy(value = formatExpirationDate(value), errorText = UiText.StringValue("")) }
        validateForm()
    }

    override fun setCVV(value: TextFieldValue) {
        _cardCvvConfig.update { _cardCvvConfig.value.copy(value = formatCvv(value), errorText = UiText.StringValue("")) }
        validateForm()
    }

    override fun invertScreenLoading() {
        _screenConfig.value = CardValidationModel(
            loading = !_screenConfig.value.loading,
            buttonEnabled = true
        )
        validateForm()
    }

    

    private fun formatCardNumber(value: TextFieldValue): TextFieldValue {
        val digits = value.text.filter { it.isDigit() }.take(16)
        val formatted = digits.chunked(4).joinToString(" ")

        val cursorPos = value.selection.start
        val unformattedCursorDigits = value.text
            .take(cursorPos)
            .count { it.isDigit() }

        val newCursor = formatted.indices
            .count { formatted
                .take(it)
                .count { counter -> counter.isDigit() } < unformattedCursorDigits
        }.coerceAtMost(formatted.length)

        return TextFieldValue(
            text = formatted,
            selection = TextRange(newCursor)
        )
    }
    
    private fun formatCvv(value: TextFieldValue): TextFieldValue = value.copy(text = value.text.filter { it.isDigit() }.take(3))

    private fun formatExpirationDate(input: TextFieldValue): TextFieldValue {
        val digits = input.text.filter { it.isDigit() }.take(4)
        val formatted = digits.chunked(2).joinToString("/")
        val digitsBeforeCursor = input.text
            .take(input.selection.start)
            .count { it.isDigit() }
        
        val newCursor = when {
            digitsBeforeCursor <= 2 -> digitsBeforeCursor
            else -> digitsBeforeCursor + 1
        }.coerceAtMost(formatted.length)

        return TextFieldValue(
            text = formatted,
            selection = TextRange(newCursor)
        )
    }
    
    private fun validateForm() {
        val numberValid = validator.isValidCardNumber(_cardNumberConfig.value.value.text)
        if(!numberValid) {
            _cardNumberConfig.update { _cardNumberConfig.value.copy(errorText = UiText.StringResource(R.string.card_invalid_number)) }
            _formValid.value = false
            return       
        }
        if(!validator.isValidCardHolderName(_cardHolderConfig.value.value.text)) {
            _cardHolderConfig.update { _cardHolderConfig.value.copy(errorText = UiText.StringResource(R.string.card_invalid_holder)) }
            _formValid.value = false
            return
        }
        if(!validator.isValidExpirationDate(_expirationDateConfig.value.value.text)) {
            _expirationDateConfig.update { _expirationDateConfig.value.copy(errorText = UiText.StringResource(R.string.card_invalid_expiration)) }
            _formValid.value = false
            return
        }
        if(!validator.isValidCvv(_cardCvvConfig.value.value.text)) {
            _cardCvvConfig.update { _cardCvvConfig.value.copy(errorText = UiText.StringResource(R.string.card_invalid_cvv)) }
            _formValid.value = false
            return
        }
        _formValid.value = true
        
    }
    

    override val cardNumberConfig: Flow<TextFieldConfig> get() = _cardNumberConfig.asSharedFlow()
    override val cardHolderConfig: Flow<TextFieldConfig> get() = _cardHolderConfig.asSharedFlow()
    override val expirationDateConfig: Flow<TextFieldConfig> get() = _expirationDateConfig.asSharedFlow()
    override val cardCvvConfig: Flow<TextFieldConfig> get() = _cardCvvConfig.asSharedFlow()
    override val formValid: Flow<Boolean> get() = _formValid.asStateFlow()
    override val screenConfig: Flow<CardValidationModel> get() = _screenConfig.asSharedFlow()
}