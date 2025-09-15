package ru.makiev.authentification

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.config.*
import java.util.*

class JwtService(config: ApplicationConfig) {
    private val secret = config.property("jwt.secret").getString()
    private val issuer = config.property("jwt.issuer").getString()
    private val audience = config.property("jwt.audience").getString()
    private val expirationDays = config.property("jwt.expirationDays").getString().toInt()

    private val algorithm = Algorithm.HMAC512(secret)

    fun generateToken(userId: Int, email: String): String {
        return JWT.create()
            .withSubject("Authentification")
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim("userId", userId)
            .withClaim("email", email)
            .withExpiresAt(getExpirationDate())
            .sign(algorithm)
    }

    fun validateToken(token: String): Boolean {
        return try {
            val verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()

            verifier.verify(token)
            true
        } catch (e: Exception){
            false
        }
    }

    fun getUserIdFromToken(token: String): String {
        val jwt = JWT.decode(token)
        return jwt.getClaim("userId").asString()
    }

    private fun getExpirationDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, expirationDays)
        return calendar.time
    }
}