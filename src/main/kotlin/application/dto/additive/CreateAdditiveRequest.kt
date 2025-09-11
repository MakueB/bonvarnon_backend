package ru.makiev.application.dto.additive

import java.math.BigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class CreateAdditiveRequest(
    val name: String,
    val extraPrice: String
) {
    fun validate(): List<String> {
        val errors = mutableListOf<String>()
        if (name.isBlank()) errors.add("Name must not me blank")
        if (extraPrice.toBigDecimal() <= BigDecimal.ZERO) errors.add("Price must be a valid number")
        return errors
    }
}
