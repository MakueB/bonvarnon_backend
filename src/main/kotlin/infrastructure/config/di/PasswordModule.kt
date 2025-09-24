package ru.makiev.infrastructure.config.di

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory
import io.ktor.server.config.ApplicationConfig
import org.koin.dsl.module
import ru.makiev.domain.api.service.PasswordService
import ru.makiev.domain.service.Argon2PasswordService

fun passwordModule(config: ApplicationConfig) = module {
    single<Argon2> {
        Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id)
    }

    single<PasswordService> {
        val argon2Config = config.config("ktor.security.argon2")
        Argon2PasswordService(
            argon2 = get(),
            iterations = argon2Config.property("iterations").getString().toInt(),
            memory = argon2Config.property("memory").getString().toInt(),
            parallelism = argon2Config.property("parallelism").getString().toInt()
        )
    }
}