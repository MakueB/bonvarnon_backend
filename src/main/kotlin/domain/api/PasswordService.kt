package ru.makiev.domain.api

interface PasswordService {
    fun hash(password: String): String
    fun verify(password: String, hash: String): Boolean
}