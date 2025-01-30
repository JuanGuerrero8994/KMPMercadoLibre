package org.example.kmpmercadolibre.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.kmpmercadolibre.data.core.Resource
import org.example.kmpmercadolibre.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): Flow<Resource<List<Product>>>
}