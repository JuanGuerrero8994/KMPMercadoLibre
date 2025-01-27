package org.example.kmpmercadolibre.ui.components.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController



@Composable
fun ScaffoldComponent(
    navController: NavController,
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
    floatingActionButton: @Composable (() -> Unit)? = null, // Nuevo parámetro para el FAB
    content: @Composable () -> Unit
) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            if (showTopBar) {
                TopAppBarComponent()
            }
        },

        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)

            }
        },
        floatingActionButton = floatingActionButton ?: {}, // Asignamos el parámetro FAB aquí
        contentColor = Color.Transparent // Aquí eliminamos el fondo del Scaffold
    ) { innerPadding ->

        // Colocamos el contenido del Scaffold
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // El contenido específico de la pantalla se coloca aquí
                content()

            }
        }
    }
}
