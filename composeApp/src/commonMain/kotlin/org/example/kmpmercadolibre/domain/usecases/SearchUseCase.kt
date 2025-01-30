package org.example.kmpmercadolibre.domain.usecases

import org.example.kmpmercadolibre.domain.repository.ProductRepository

class ProductsUseCase(private val repository: ProductRepository) {
    suspend fun getAllProducts() = repository.getAllProducts()
}