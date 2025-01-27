package org.example.kmpmercadolibre.domain.model

data class SearchResult(
    val query: String,             // La consulta que el usuario buscó
    val totalResults: Int,         // Total de resultados encontrados
    val results: List<Item>        // Lista de elementos (productos) encontrados
)