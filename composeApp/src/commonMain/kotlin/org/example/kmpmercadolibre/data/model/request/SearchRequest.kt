package org.example.kmpmercadolibre.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest (
    val query:String
)