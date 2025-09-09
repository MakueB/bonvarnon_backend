package ru.makiev.infrastructure.config.di

import org.koin.dsl.module
import ru.makiev.data.repository.MenuCategoryRepositoryImpl
import ru.makiev.data.repository.MenuItemRepositoryImpl
import ru.makiev.domain.api.AdditiveRepository
import ru.makiev.domain.api.MenuCategoryRepository
import ru.makiev.domain.api.MenuItemRepository
import ru.makiev.infrastructure.repository.AdditiveRepositoryImpl

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
}