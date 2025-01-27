package org.example.kmpmercadolibre

import android.app.Application
import org.example.kmpmercadolibre.di.initKoin

class MercadoLibreApp:Application() {
    override fun onCreate() {
        initKoin()
        super.onCreate()
    }
}