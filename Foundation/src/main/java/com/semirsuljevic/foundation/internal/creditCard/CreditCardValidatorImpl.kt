package com.semirsuljevic.foundation.internal.creditCard

import com.semirsuljevic.foundation.api.creditCard.CreditCardConfig
import com.semirsuljevic.foundation.api.creditCard.CreditCardValidator
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

internal class CreditCardValidatorImpl @Inject constructor(
    
): CreditCardValidator {
    override fun isValidCardNumber(cardNumber: String): Boolean {
        val cleaned = cardNumber.replace(" ", "")
        // Check length and digits only
        return !(cleaned.length != 16 || !cleaned.all { it.isDigit() })
    }

    override fun isValidExpirationDate(expiration: String): Boolean {
        // Must be exactly 5 chars: MM/YY
        if (expiration.length != 5) return false
        if (expiration[2] != '/') return false

        val formatter = DateTimeFormatter.ofPattern(CreditCardConfig.EXPIRATION_DATE_FORMAT)

        return try {
            val yearMonth = YearMonth.parse(expiration, formatter)
            val now = YearMonth.now()
            !yearMonth.isBefore(now) // expiration month must be current or future
        } catch (e: DateTimeParseException) {
            false
        }
    }

    override fun isValidCvv(cvv: String): Boolean {
        return (cvv.length == 3 || cvv.length == 4) && cvv.all { it.isDigit() }
    }

    override fun isValidCardHolderName(name: String): Boolean {
        val trimmed = name.trim()

        if (trimmed.length < 2 || trimmed.length > 30) return false
        val regex = CreditCardConfig.CARD_HOLDER_REGEX

        return regex.matches(trimmed)
    }
}