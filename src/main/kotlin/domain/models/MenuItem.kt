package ru.makiev.domain.models

import java.math.BigDecimal

data class MenuItem(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val imageUrl: String?
)
