package org.example.kmpmercadolibre.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(
    val rate: Float,
    val count: Int
)