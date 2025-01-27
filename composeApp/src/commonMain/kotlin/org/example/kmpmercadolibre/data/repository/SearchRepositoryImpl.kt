package org.example.kmpmercadolibre.data.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.kmpmercadolibre.data.core.Resource
import org.example.kmpmercadolibre.data.mapper.SearchMapper
import org.example.kmpmercadolibre.data.model.response.SearchResponse
import org.example.kmpmercadolibre.data.remote.Config
import org.example.kmpmercadolibre.data.remote.MercadoLibreApi
import org.example.kmpmercadolibre.data.remote.buildUrl
import org.example.kmpmercadolibre.domain.model.SearchResult
import org.example.kmpmercadolibre.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val api: MercadoLibreApi
) : SearchRepository {

    override suspend fun search(query: String): Flow<Resource<SearchResult>> = flow {
        emit(Resource.Loading)
        try {
            val searchResponse = api.httpClient.get{
               buildUrl(
                   endpoint = Config.SEARCH_ENDPOINT,
                   queryParams =  mapOf("q" to query)
               )
            }.body<SearchResponse>()

            emit(Resource.Success(SearchMapper.toDomain(searchResponse)))

        }
        catch (e:Exception){
            emit(Resource.Error(Exception("${e.message}")))
        }
    }
}

