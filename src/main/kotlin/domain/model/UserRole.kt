package ru.makiev.domain.model

enum class UserRole(val id: Int) {
    CUSTOMER(1),
    ADMIN(2),
    MANAGER(3);

    companion object{
        fun fromId(id: Int): UserRole =
            entries.find { it.id == id } ?: CUSTOMER
    }
}