package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.repository.DataStoreRepository

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    dataStore : DataStoreRepository
){
    NavHost(
        navController= navController,
        startDestination = Screen.Onboarding.route) {
            composable(
                route = Screen.Onboarding.route
            ){
                Onboarding(navController, dataStore)
            }
            composable(
                route = Screen.Home.route
            ){
                HomeScreen(navController)
            }
            composable(
                route = Screen.Profile.route,
            ){
                ProfileScreen(navController, dataStore)
            }
    }
}