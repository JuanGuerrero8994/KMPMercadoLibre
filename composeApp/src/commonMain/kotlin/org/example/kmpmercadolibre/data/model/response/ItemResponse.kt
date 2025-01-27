package org.example.kmpmercadolibre.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val permalink: String
)