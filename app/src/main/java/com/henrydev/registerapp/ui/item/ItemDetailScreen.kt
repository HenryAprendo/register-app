package com.henrydev.registerapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.registerapp.ui.AppViewModelProvider
import com.henrydev.registerapp.ui.navigation.NavigationDestination
import com.henrydev.registerapp.R
import com.henrydev.registerapp.RegisterTopAppBar

object ItemDetailDestination: NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_details
    const val itemIdArg = "itemId"
    val routeWithArg = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RegisterTopAppBar(
                title = stringResource(ItemDetailDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            ItemDetail(uiState.itemDetails)
        }
    }
}

@Composable
fun ItemDetail(
    itemDetails: ItemDetails,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier.padding(horizontal = 16.dp)) {
                Text("Name")
                Spacer(Modifier.weight(1f))
                Text(itemDetails.name)
            }
            Row(modifier.padding(horizontal = 16.dp)) {
                Text("Price")
                Spacer(Modifier.weight(1f))
                Text(itemDetails.price)
            }
            Row(modifier.padding(horizontal = 16.dp)) {
                Text("Quantity")
                Spacer(Modifier.weight(1f))
                Text(itemDetails.quantity)
            }
        }
    }
}

















