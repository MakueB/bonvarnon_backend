package ru.makiev.domain.model

import java.time.LocalDateTime

data class User(
    val id: Int,
    val email: String?,
    val phone: String?,
    val name: String,
    val avatarUrl: String?,
    val role: UserRole,
    val bonusBalance: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    fun canUseBonus(amount: Int): Boolean = bonusBalance >= amount

    fun addBonus(amount: Int): User = copy(bonusBalance = bonusBalance + amount)

    fun spendBonus(amount: Int): User {
        require(canUseBonus(amount)) {"Not enough bonus balance"}
        return copy(bonusBalance = bonusBalance - amount)
    }
}
