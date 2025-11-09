package com.henrydev.registerapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.henrydev.registerapp.RegisterTopAppBar
import com.henrydev.registerapp.ui.navigation.NavigationDestination

object ItemEntryDestination: NavigationDestination {
    override val route = "item_entry"
    override val title = "Add item"
}

@Composable
fun ItemEntryScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            RegisterTopAppBar(ItemEntryDestination.title)
        },
        modifier = modifier
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            Text("Add Items")
        }

    }

}