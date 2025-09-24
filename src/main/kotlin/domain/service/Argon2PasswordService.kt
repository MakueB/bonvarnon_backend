package ru.makiev.domain.service

import de.mkammerer.argon2.Argon2
import ru.makiev.domain.api.service.PasswordService

class Argon2PasswordService(
    private val argon2: Argon2,
    private val iterations: Int = 10,
    private val memory: Int = 65536,
    private val parallelism: Int = 1
): PasswordService {
    override fun hash(password: String): String {
        return try {
            argon2.hash(iterations,memory,parallelism, password.toCharArray())
        } finally {
            argon2.wipeArray(password.toCharArray())
        }

    }

    override fun verify(password: String, hash: String): Boolean {
        return try {
            argon2.verify(hash, password.toCharArray())
        } finally {
            argon2.wipeArray(password.toCharArray())
        }
    }
}