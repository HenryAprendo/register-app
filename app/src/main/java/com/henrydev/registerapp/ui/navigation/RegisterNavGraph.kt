package com.henrydev.registerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.henrydev.registerapp.ui.home.HomeDestination
import com.henrydev.registerapp.ui.home.HomeScreen
import com.henrydev.registerapp.ui.item.ItemDetailDestination
import com.henrydev.registerapp.ui.item.ItemDetailScreen
import com.henrydev.registerapp.ui.item.ItemEditDestination
import com.henrydev.registerapp.ui.item.ItemEditScreen
import com.henrydev.registerapp.ui.item.ItemEntryDestination
import com.henrydev.registerapp.ui.item.ItemEntryScreen
import com.henrydev.registerapp.ui.movement.EntryMovementDestination
import com.henrydev.registerapp.ui.movement.EntryMovementScreen

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
                onItemClick = { item ->
                    navController.navigate("${ItemDetailDestination.route}/${item.id}")
                }
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

        composable(
            route = ItemDetailDestination.routeWithArg,
            arguments = listOf(navArgument(name = ItemDetailDestination.itemIdArg){
                type = NavType.IntType
            })
        ) {
            ItemDetailScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = { item ->
                    navController.navigate("${ItemEditDestination.route}/${item.id}")
                },
                navigateToEntryMovement = {
                    navController.navigate(
                        route = "${EntryMovementDestination.route}/${it.id}"
                    )
                },
                navigateToOutwardMovement = {
                    navController.navigate(route = "outward_move")
                }
            )
        }

        composable(
            route = ItemEditDestination.routeWithArg,
            arguments = listOf(navArgument(name = ItemDetailDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = EntryMovementDestination.routeWithArg,
            arguments = listOf(
                navArgument(name = EntryMovementDestination.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            EntryMovementScreen()
        }

    }

}


