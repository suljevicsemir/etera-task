package com.semirsuljevic.api.validation.config

data class CardValidationModel(
    val buttonEnabled: Boolean = false,
    val loading: Boolean = false
)