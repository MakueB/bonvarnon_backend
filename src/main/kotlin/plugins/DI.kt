package ru.makiev.plugins

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import ru.makiev.infrastructure.config.di.dataModule
import ru.makiev.infrastructure.config.di.passwordModule
import ru.makiev.infrastructure.config.di.repositoryModule
import ru.makiev.infrastructure.config.di.serviceModule

fun Application.configureDi() {
    install(Koin) {
        slf4jLogger()
        modules(dataModule, repositoryModule, serviceModule, passwordModule(environment.config))
    }
}