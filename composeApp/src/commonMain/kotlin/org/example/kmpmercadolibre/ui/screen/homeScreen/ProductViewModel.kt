package org.example.kmpmercadolibre.ui.screen.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.kmpmercadolibre.data.core.Resource
import org.example.kmpmercadolibre.domain.model.Product
import org.example.kmpmercadolibre.domain.usecases.ProductsUseCase

class ProductViewModel(private val useCase: ProductsUseCase) : ViewModel() {

    private val _productsState = MutableStateFlow<Resource<List<Product>>>(Resource.Loading)
    val productsState: StateFlow<Resource<List<Product>>> get() = _productsState

    fun getAllProduct() {
        viewModelScope.launch {
            useCase.getAllProducts().collect { resource ->
                _productsState.value = resource
            }
        }
    }
}