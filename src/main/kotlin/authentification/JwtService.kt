package ru.makiev.authentification

import ch.qos.logback.core.util.OptionHelper.getEnv
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtService {
    private val issuer = "makiev-misha"
    private val jwtSecret = getEnv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC512(jwtSecret)

    private val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()
}