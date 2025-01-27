package org.example.kmpmercadolibre.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse(
    @SerialName("total") val total: Int
)