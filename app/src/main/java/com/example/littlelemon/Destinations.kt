package com.example.littlelemon

sealed class Screen(val route : String) {
    object Onboarding : Screen(route = "Onboarding")
    object Home : Screen(route = "Home")
    object Profile : Screen(route = "Profile")
}