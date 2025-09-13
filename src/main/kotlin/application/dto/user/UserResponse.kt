package ru.makiev.application.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val email: String?,
    val phone: String?,
    val name: String,
    val avatarUrl: String?,
    val role: String,
    val bonusBalance: Int,
    val createdAt: String,
    val updatedAt: String
)
