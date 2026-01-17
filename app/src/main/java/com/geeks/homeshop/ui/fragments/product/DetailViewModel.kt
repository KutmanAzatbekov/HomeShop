package com.geeks.homeshop.ui.fragments.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.homeshop.domain.models.Product
import com.geeks.homeshop.domain.usecases.GetProductByIdUseCase
import com.geeks.homeshop.ui.models.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase
): ViewModel() {

    private val _state = MutableStateFlow<UiState<Product>>(UiState.Loading)

    val state: StateFlow<UiState<Product>> = _state.asStateFlow()

    fun load(id: Int){
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val product = getProductByIdUseCase(id)
                _state.value = UiState.Success(product)
            } catch (e: Exception){
                _state.value = UiState.Error(e.message ?: "Error")
            }
        }
    }

}