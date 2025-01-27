package org.example.kmpmercadolibre.ui.screen.navigation

sealed class Destinations(val route: String) {

    //SPLASHSCREEN
    data object SplashScreen : Destinations("splashScreen")

    //HOME  SCREEN
    data object HomeScreen : Destinations("homeScreen")




}