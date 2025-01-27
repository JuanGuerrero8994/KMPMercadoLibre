package org.example.kmpmercadolibre.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.kmpmercadolibre.data.core.Resource
import org.example.kmpmercadolibre.domain.model.SearchResult

interface SearchRepository {
    suspend fun search(query: String): Flow<Resource<SearchResult>>
}