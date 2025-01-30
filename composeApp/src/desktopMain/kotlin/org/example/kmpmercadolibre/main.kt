package org.example.kmpmercadolibre

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.kmpmercadolibre.di.initKoin
import org.example.kmpmercadolibre.ui.App


fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Mercadolibre",
    ) {
        App()
    }
}

