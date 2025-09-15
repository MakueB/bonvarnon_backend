package ru.makiev.infrastructure.config.di

import io.ktor.server.application.Application
import io.ktor.server.config.ApplicationConfig
import org.koin.dsl.module

val dataModule = module {
    single<ApplicationConfig> {
        get<Application>().environment.config
    }
}