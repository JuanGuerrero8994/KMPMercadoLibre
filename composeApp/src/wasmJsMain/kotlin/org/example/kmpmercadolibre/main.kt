package org.example.kmpmercadolibre

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.example.kmpmercadolibre.di.initKoin
import org.example.kmpmercadolibre.ui.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    //initKoin()
    ComposeViewport(document.body!!) {
        App()
    }
}