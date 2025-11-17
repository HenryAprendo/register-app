package com.henrydev.registerapp.ui.movement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.henrydev.registerapp.ui.navigation.NavigationDestination
import com.henrydev.registerapp.R

object EntryMovementDestination: NavigationDestination {
    override val route: String = "entry_move"
    override val titleRes: Int = R.string.entry_move
    val itemIdArg = "itemId"
    val routeWithArg = "$route/{$itemIdArg}"
}

@Composable
fun EntryMovementScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Movement screen")
    }
}













