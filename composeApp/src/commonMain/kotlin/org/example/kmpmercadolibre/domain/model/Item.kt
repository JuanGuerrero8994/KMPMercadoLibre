package org.example.kmpmercadolibre.domain.model

data class Item(
    val id: String,                // ID único del producto
    val title: String,             // Título o nombre del producto
    val price: Double,             // Precio del producto
    val thumbnail: String,         // URL de la imagen en miniatura del producto
    val permalink: String          // URL al producto en MercadoLibre
)