package ru.makiev.application.dto.auth

sealed class AuthRequest {
    data class EmailAuthRequest(
        val email: String,
        val password: String
    ) : AuthRequest()

    data class PhoneAuthRequest(
        val phone: String,
        val code: String
    ) : AuthRequest()

    data class OAuthRequest(
        val provider: String,
        val token: String
    ) : AuthRequest()
}
