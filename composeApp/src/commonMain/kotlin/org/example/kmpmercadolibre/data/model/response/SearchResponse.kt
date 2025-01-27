package org.example.kmpmercadolibre.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SearchResponse(
    @SerialName("query") val query: String,
    @SerialName("paging") val paging: PagingResponse,
    @SerialName("results") val results: List<ItemResponse>
)