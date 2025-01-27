package org.example.kmpmercadolibre.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("price") val price: Double,
    @SerialName("thumbnail") val thumbnail: String,
    @SerialName("permalink") val permalink: String
)