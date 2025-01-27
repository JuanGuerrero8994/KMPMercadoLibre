package org.example.kmpmercadolibre.ui.screen.favouriteScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.example.kmpmercadolibre.ui.components.scaffold.ScaffoldComponent

@Composable
fun FavouriteScreen(navController: NavController){
    ScaffoldComponent(navController){
        Text("Favourite")
    }
}