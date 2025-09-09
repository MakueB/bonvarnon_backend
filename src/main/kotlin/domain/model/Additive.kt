package ru.makiev.domain.model

import java.math.BigDecimal

data class Additive(
    val id: Int,
    val name: String,
    val extraPrice: BigDecimal
)
