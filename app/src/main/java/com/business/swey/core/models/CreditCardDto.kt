package com.business.swey.core.models

data class CreditCardDto(
    val id: Int,
    val network: String,
    val lastFourDigits: Int,
    val amount: Int
)
