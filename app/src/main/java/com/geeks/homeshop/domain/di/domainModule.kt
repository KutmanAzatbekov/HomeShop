package com.geeks.homeshop.domain.di

import com.geeks.homeshop.domain.usecases.AddToCartUseCase
import com.geeks.homeshop.domain.usecases.CheckoutUseCase
import com.geeks.homeshop.domain.usecases.ClearCartUseCase
import com.geeks.homeshop.domain.usecases.GetCartItemsUseCase
import com.geeks.homeshop.domain.usecases.GetProductByIdUseCase
import com.geeks.homeshop.domain.usecases.GetProductsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(get()) }
    factory { GetProductByIdUseCase(get()) }
    factoryOf(::AddToCartUseCase)
    factoryOf(::GetCartItemsUseCase)
    factoryOf(::ClearCartUseCase)
    factoryOf(::CheckoutUseCase)
}

