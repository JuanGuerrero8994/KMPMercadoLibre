package org.example.kmpmercadolibre.domain.usecases

import org.example.kmpmercadolibre.domain.repository.SearchRepository

class SearchUseCase(private val repository: SearchRepository) {
    suspend fun search(query: String) = repository.search(query)
}