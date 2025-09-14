package ru.makiev.infrastructure.config.di

import org.koin.dsl.module
import ru.makiev.domain.api.AdditiveService
import ru.makiev.domain.api.MenuService
import ru.makiev.domain.api.UserService
import ru.makiev.domain.service.AdditiveServiceImpl
import ru.makiev.domain.service.MenuServiceImpl
import ru.makiev.domain.service.UserSeviceImpl

val serviceModule = module {
    single<MenuService> {
        MenuServiceImpl(get(), get())
    }

    single<AdditiveService> {
        AdditiveServiceImpl(get())
    }

    single<UserService> {
        UserSeviceImpl(get(),get())
    }
}