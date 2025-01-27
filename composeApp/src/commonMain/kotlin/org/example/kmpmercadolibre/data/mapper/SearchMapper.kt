package org.example.kmpmercadolibre.data.mapper

import org.example.kmpmercadolibre.data.model.request.ItemRequest
import org.example.kmpmercadolibre.data.model.response.ItemResponse
import org.example.kmpmercadolibre.data.model.response.PagingResponse
import org.example.kmpmercadolibre.data.model.request.SearchRequest
import org.example.kmpmercadolibre.data.model.response.SearchResponse
import org.example.kmpmercadolibre.domain.model.Item
import org.example.kmpmercadolibre.domain.model.SearchResult



object SearchMapper {
    // Mapeo de SearchResponse (capa de datos) a SearchResult (capa de dominio)
    fun toDomain(searchResponse: SearchResponse): SearchResult {
        return SearchResult(
            query = searchResponse.query,
            totalResults = searchResponse.paging.total,
            results = searchResponse.results.map { toDomainItem(it) }
        )
    }

    // Mapeo de ItemResponse (capa de datos) a Item (capa de dominio)
    private fun toDomainItem(itemResponse: ItemResponse): Item {
        return Item(
            id = itemResponse.id,
            title = itemResponse.title,
            price = itemResponse.price,
            thumbnail = itemResponse.thumbnail,
            permalink = itemResponse.permalink
        )
    }

    // Mapeo de SearchResult (capa de dominio) a SearchResponse (capa de datos)
    fun toResponse(searchResult: SearchResult): SearchResponse {
        return SearchResponse(
            query = searchResult.query,
            paging = PagingResponse(total = searchResult.totalResults),
            results = searchResult.results.map { toResponseItem(it) }
        )
    }

    // Mapeo de Item (capa de dominio) a ItemResponse (capa de datos)
    private fun toResponseItem(item: Item): ItemResponse {
        return ItemResponse(
            id = item.id,
            title = item.title,
            price = item.price,
            thumbnail = item.thumbnail,
            permalink = item.permalink
        )
    }

    // Mapeo de SearchResult (capa de dominio) a SearchRequest (capa de datos)
    fun toRequest(searchResult: SearchResult): SearchRequest {
        return SearchRequest(
            query = searchResult.query
        )
    }

    // Mapeo de Item (capa de dominio) a ItemRequest (capa de datos)
    private fun toRequestItem(item: Item): ItemRequest {
        return ItemRequest(
            id = item.id,
            title = item.title,
            price = item.price,
            thumbnail = item.thumbnail,
            permalink = item.permalink
        )
    }
}
