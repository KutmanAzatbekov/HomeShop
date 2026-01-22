package com.geeks.homeshop.ui.di

import com.geeks.homeshop.ui.fragments.product.detail.DetailViewModel
import com.geeks.homeshop.ui.fragments.product.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val uiModule = module {

    viewModel { ListViewModel(get()) }

    viewModel { DetailViewModel(get()) }

}
