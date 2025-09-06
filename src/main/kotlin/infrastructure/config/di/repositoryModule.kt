package ru.makiev.infrastructure.config.di

import org.koin.dsl.module
import ru.makiev.data.repository.MenuCategoryRepositoryImpl
import ru.makiev.data.repository.MenuItemRepositoryImpl
import ru.makiev.domain.api.MenuCategoryRepository
import ru.makiev.domain.api.MenuItemRepository

val repositoryModule = module {
    single<MenuCategoryRepository> {
        MenuCategoryRepositoryImpl()
    }

    single<MenuItemRepository> {
        MenuItemRepositoryImpl()
    }
}