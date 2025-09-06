package ru.makiev.infrastructure.config.di

import org.koin.dsl.module
import ru.makiev.domain.api.MenuService
import ru.makiev.domain.service.MenuServiceImpl

val serviceModule = module {
    single<MenuService> {
        MenuServiceImpl(get(), get())
    }
}