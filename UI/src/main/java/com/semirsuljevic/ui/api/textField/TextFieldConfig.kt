package com.semirsuljevic.ui.api.textField

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.semirsuljevic.ui.api.common.UiText

data class TextFieldConfig(
    val value: TextFieldValue = TextFieldValue(),
    val hint: UiText = UiText.StringValue(""),
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val errorText: UiText = UiText.StringValue("")
)

