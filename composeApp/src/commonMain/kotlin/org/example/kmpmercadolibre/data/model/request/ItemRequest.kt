package org.example.kmpmercadolibre.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ItemRequest(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val permalink: String
)