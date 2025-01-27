package org.example.kmpmercadolibre

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.kmpmercadolibre.di.initKoin
import org.example.kmpmercadolibre.ui.App
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.defaultImageResultMemoryCache
import okio.Path.Companion.toOkioPath
import java.io.File

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Mercadolibre",
    ) {
        App()
    }
}

