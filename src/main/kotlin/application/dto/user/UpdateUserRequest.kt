package ru.makiev.application.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val id: Int,
    val email: String? = null,
    val phone: String? = null,
    val name: String? = null,
    val avatarUrl: String? = null,
    val roleId: Int? = null,
    val bonusBalance: Int? = null
)
