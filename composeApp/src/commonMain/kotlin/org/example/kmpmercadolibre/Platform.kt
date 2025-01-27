package org.example.kmpmercadolibre

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initLogger()
