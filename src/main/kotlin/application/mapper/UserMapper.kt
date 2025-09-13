package ru.makiev.application.mapper

import ru.makiev.application.dto.user.UserResponse
import ru.makiev.domain.model.User

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = this.id,
        email = this.email,
        phone = this.phone,
        name = this.name,
        avatarUrl = this.avatarUrl,
        role = this.role.name,
        bonusBalance = this.bonusBalance,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString()
    )
}