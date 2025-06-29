package com.semirsuljevic.ui.api.textField

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.semirsuljevic.ui.api.theme.AppSizes

@Composable
fun AppTextField(
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    config: TextFieldConfig = TextFieldConfig(),
    enabled: Boolean = true
    
) {
    Column (
        horizontalAlignment = Alignment.Start
    ){
        TextField(
            value = config.value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
            label = { Text(config.hint.asString(), color = MaterialTheme.colorScheme.onSurface) },
            keyboardOptions = config.keyboardOptions, 
            visualTransformation = config.visualTransformation,
            enabled = enabled,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = modifier,
            
        )
        if(config.errorText.asString().isNotEmpty())
        Text(
            config.errorText.asString(),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(top = AppSizes.xxSmall)
        )
    }
}