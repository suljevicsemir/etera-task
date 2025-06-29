package com.semirsuljevic.foundation.api.creditCard

interface CreditCardValidator {
    fun isValidCardNumber(cardNumber: String): Boolean
    fun isValidExpirationDate(expiration: String): Boolean
    fun isValidCvv(cvv: String): Boolean
    fun isValidCardHolderName(name: String): Boolean
}