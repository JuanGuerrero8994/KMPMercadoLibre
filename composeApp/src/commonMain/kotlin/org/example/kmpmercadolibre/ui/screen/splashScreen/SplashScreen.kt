package org.example.kmpmercadolibre.ui.screen.splashScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.NavController
import kmpmercadolibre.composeapp.generated.resources.Res
import kmpmercadolibre.composeapp.generated.resources.logo_mercadolibre
import kotlinx.coroutines.delay
import org.example.kmpmercadolibre.ui.components.scaffold.BottomNavScreen
import org.example.kmpmercadolibre.ui.screen.navigation.Destinations
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(navController: NavController) {
    // Animación de opacidad
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Animación de fade-in
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500) // Duración de fade-in
        )
        delay(2000) // Mostrar el logo durante 2 segundos
        // Navegar a la siguiente pantalla
        navController.navigate(BottomNavScreen.Home.route) {
            popUpTo(Destinations.SplashScreen.route) { inclusive = true }
        }
    }

    // Diseño de la pantalla de bienvenida
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.logo_mercadolibre), // Reemplaza con el ID del logo
            contentDescription = "Logo de MercadoLibre",
            modifier = Modifier.alpha(alpha.value),
        )
    }
}