package ru.makiev.infrastructure.config.di

import org.koin.dsl.module
import ru.makiev.data.repository.MenuCategoryRepositoryImpl
import ru.makiev.data.repository.MenuItemRepositoryImpl
import ru.makiev.domain.api.repository.AdditiveRepository
import ru.makiev.domain.api.repository.MenuCategoryRepository
import ru.makiev.domain.api.repository.MenuItemRepository
import ru.makiev.domain.api.repository.OrderRepostory
import ru.makiev.domain.api.repository.SizeRepository
import ru.makiev.domain.api.repository.UserRepository
import ru.makiev.infrastructure.repository.AdditiveRepositoryImpl
import ru.makiev.infrastructure.repository.OrderRepositoryImpl
import ru.makiev.infrastructure.repository.SizeRepositoryImpl
import ru.makiev.infrastructure.repository.UserRepositoryImpl

val repositoryModule = module {
    single<MenuCategoryRepository> {
        MenuCategoryRepositoryImpl()
    }

    single<MenuItemRepository> {
        MenuItemRepositoryImpl()
    }

    single<AdditiveRepository> {
        AdditiveRepositoryImpl()
    }

    single<UserRepository> {
        UserRepositoryImpl()
    }

    single<OrderRepostory> {
        OrderRepositoryImpl()
    }

    single<AdditiveRepository> {
        AdditiveRepositoryImpl()
    }

    single<SizeRepository> {
        SizeRepositoryImpl()
    }
}