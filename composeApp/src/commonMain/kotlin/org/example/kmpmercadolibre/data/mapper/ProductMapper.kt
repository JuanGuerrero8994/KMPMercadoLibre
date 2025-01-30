package org.example.kmpmercadolibre.data.mapper

import org.example.kmpmercadolibre.data.model.response.ListProductResponse
import org.example.kmpmercadolibre.data.model.response.ProductResponse
import org.example.kmpmercadolibre.domain.model.Product
import org.example.kmpmercadolibre.domain.model.Rating


object ProductMapper {


    // Para convertir un solo producto
    private fun toDomain(productResponse: ProductResponse) =
        Product(
            id = productResponse.id,
            title = productResponse.title,
            price = productResponse.price,
            description = productResponse.description,
            category = productResponse.category,
            image = productResponse.image,
            rating = Rating(
                rate = productResponse.rating.rate,
                count = productResponse.rating.count
            )
        )


    // Para convertir una lista de productos
    fun toDomainList(productListResponse: List<ProductResponse>): List<Product> =
        productListResponse.map { toDomain(it) }

}
