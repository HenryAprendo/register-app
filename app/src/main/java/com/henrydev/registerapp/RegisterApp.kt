package com.henrydev.registerapp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.henrydev.registerapp.ui.navigation.RegisterNavHost

@Composable
fun RegisterApp(
    modifier: Modifier = Modifier
) {
    val controller = rememberNavController()
    RegisterNavHost(navController = controller, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier
    )
}
