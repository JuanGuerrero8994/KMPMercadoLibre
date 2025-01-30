package org.example.kmpmercadolibre.data.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.kmpmercadolibre.data.core.Resource
import org.example.kmpmercadolibre.data.mapper.ProductMapper
import org.example.kmpmercadolibre.data.model.response.ListProductResponse
import org.example.kmpmercadolibre.data.model.response.ProductResponse
import org.example.kmpmercadolibre.data.remote.Config
import org.example.kmpmercadolibre.data.remote.EccomerceApi
import org.example.kmpmercadolibre.data.remote.buildUrl
import org.example.kmpmercadolibre.domain.model.Product
import org.example.kmpmercadolibre.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: EccomerceApi
) : ProductRepository {

    override suspend fun getAllProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading)
        try {
            val response:List<ProductResponse> =
                api.httpClient.get {
                    buildUrl(endpoint = Config.GET_ALL_PRODUCT_ENDPOINT)
                }.body()

            val productMapper = ProductMapper.toDomainList(response)

            emit(Resource.Success(productMapper))

        } catch (e: Exception) {
            emit(Resource.Error(Exception("${e.message}")))
        }
    }
}

