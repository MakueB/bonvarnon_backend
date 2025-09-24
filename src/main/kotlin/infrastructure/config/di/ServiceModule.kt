package ru.makiev.infrastructure.config.di

import org.koin.dsl.module
import ru.makiev.domain.api.service.AdditiveService
import ru.makiev.domain.api.service.MenuService
import ru.makiev.domain.api.service.OrderService
import ru.makiev.domain.api.service.UserService
import ru.makiev.domain.service.AdditiveServiceImpl
import ru.makiev.domain.service.MenuServiceImpl
import ru.makiev.domain.service.OrderServiceImpl
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

    single<OrderService> {
        OrderServiceImpl(get(),get(),get(),get(),get())
    }
}