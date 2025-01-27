package org.example.kmpmercadolibre.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.example.kmpmercadolibre.ui.components.scaffold.BottomNavScreen
import org.example.kmpmercadolibre.ui.screen.favouriteScreen.FavouriteScreen
import org.example.kmpmercadolibre.ui.screen.homeScreen.HomeScreen
import org.example.kmpmercadolibre.ui.screen.splashScreen.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(navController = navController, startDestination = Destinations.SplashScreen.route) {
        // Rutas simples
        addRoute(navController, Destinations.SplashScreen.route) { SplashScreen(it) }
        addBottomNavRoute(navController, BottomNavScreen.Home.route) { HomeScreen(it) }
        addBottomNavRoute(navController, BottomNavScreen.Favourite.route) { FavouriteScreen(it) }

    }
}
