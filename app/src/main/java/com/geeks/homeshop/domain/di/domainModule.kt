package com.geeks.homeshop.domain.di

import com.geeks.homeshop.domain.usecases.GetProductByIdUseCase
import com.geeks.homeshop.domain.usecases.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get()) }
    factory { GetProductByIdUseCase(get()) }
}

