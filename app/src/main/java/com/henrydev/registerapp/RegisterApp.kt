package com.henrydev.registerapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
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
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}
