package ru.makiev.application.dto.size

import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CreateSizeRequest(
    val name: String,
    val extraPrice: String
) {
    fun validate(): List<String>{
        val errors = mutableListOf<String>()
        if (name.isBlank()) errors.add("Name must not be blank")
        if (extraPrice.toBigDecimal() <= BigDecimal.ZERO) errors.add("Price must be a valid number")
        return errors
    }
}
