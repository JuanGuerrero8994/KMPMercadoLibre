package org.example.kmpmercadolibre.data.model.response

import kotlinx.serialization.Serializable
import org.example.kmpmercadolibre.domain.model.Product

@Serializable
data class ListProductResponse(val products: List<ProductResponse>)
