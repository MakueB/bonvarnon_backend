package ru.makiev.infrastructure.config.di

import org.koin.dsl.module
import ru.makiev.authentification.JwtService

val authentificationModule = module {
    single {
        JwtService(get())
    }
}