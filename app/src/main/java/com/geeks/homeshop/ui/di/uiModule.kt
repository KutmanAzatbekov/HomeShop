package com.geeks.homeshop.ui.di

import com.geeks.homeshop.ui.fragments.cart.CartViewModel
import com.geeks.homeshop.ui.fragments.product.detail.DetailViewModel
import com.geeks.homeshop.ui.fragments.product.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val uiModule = module {

    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::CartViewModel)

}
