package ru.makiev.domain.model

import java.math.BigDecimal
import java.time.Instant

data class Size(
    val id: Int,
    val name: String,
    val extraPrice: BigDecimal,
    val createdAt: Instant,
    val updatedAt: Instant
)
