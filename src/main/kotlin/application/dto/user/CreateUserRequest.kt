package ru.makiev.application.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val email: String? = null,
    val phone: String? = null,
    val name: String,
    val password: String,
    val avatarUrl: String? = null,
    val roleId: Int = 1
) {
    fun validate(): List<String> {
        val errors = mutableListOf<String>()
        if (name.isBlank()) errors.add("Name must not be blank")
        if ((email == null || email.isBlank()) && (phone == null || phone.isBlank())) {
            errors.add("Either email or phone must be provided")
        }
        if (password.length < 6) errors.add("Password must be at least 6 characters long")
        return errors
    }
}
