package com.henrydev.registerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.henrydev.registerapp.ui.home.HomeDestination
import com.henrydev.registerapp.ui.home.HomeScreen
import com.henrydev.registerapp.ui.item.ItemEntryDestination
import com.henrydev.registerapp.ui.item.ItemEntryScreen

@Composable
fun RegisterNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {

        composable(
            route = HomeDestination.route
        ) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(ItemEntryDestination.route)
                },

            )
        }

        composable(
            route = ItemEntryDestination.route
        ) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

    }

}




