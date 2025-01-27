package org.example.kmpmercadolibre.data.remote

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.path
import io.ktor.http.takeFrom

fun HttpRequestBuilder.buildUrl(endpoint: String, queryParams: Map<String, String> = emptyMap()) {
    url {
        takeFrom(Config.BASE_URL)
        path(endpoint)
        queryParams.forEach { (key, value) ->
            parameters.append(key, value)
        }
        accept(ContentType.Application.Json)
    }
}