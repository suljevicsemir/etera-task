package com.semirsuljevic.foundation.api.creditCard

object CreditCardConfig {
    const val EXPIRATION_DATE_FORMAT = "MM/yy"
    val CARD_HOLDER_REGEX = Regex("^[A-Za-z .'-]+$");
}