package com.geeks.homeshop.ui.fragments.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.homeshop.domain.usecases.CheckoutUseCase
import com.geeks.homeshop.domain.usecases.ClearCartUseCase
import com.geeks.homeshop.domain.usecases.GetCartItemsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val checkoutUseCase: CheckoutUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    val items = getCartItemsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val total = items.map { list ->
        list.sumOf {
            it.product.price * it.quantity
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun checkout() {
        viewModelScope.launch {
            val result = checkoutUseCase()
            result
                .onSuccess { clearCartUseCase() }
        }
    }
}