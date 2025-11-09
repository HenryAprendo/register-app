package com.henrydev.registerapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.registerapp.RegisterTopAppBar
import com.henrydev.registerapp.ui.AppViewModelProvider
import com.henrydev.registerapp.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route = "home"
    override val title = "Register"
}

@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            RegisterTopAppBar(HomeDestination.title)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add an item"
                )
            }
        }
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            Text("Hello Register")
        }

    }
}