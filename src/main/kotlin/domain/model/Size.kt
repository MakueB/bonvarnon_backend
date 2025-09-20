package ru.makiev.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Size(
    val id: Int,
    val name: String,
    val extraPrice: BigDecimal,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
