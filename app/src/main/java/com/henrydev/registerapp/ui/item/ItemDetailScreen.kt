package com.henrydev.registerapp.ui.item

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.registerapp.ui.AppViewModelProvider
import com.henrydev.registerapp.ui.navigation.NavigationDestination
import com.henrydev.registerapp.R
import com.henrydev.registerapp.RegisterTopAppBar
import com.henrydev.registerapp.data.Item

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
        ItemDetailBody(
            itemDetails = uiState.itemDetails,
            modifier = modifier.padding(
                top = innerPadding.calculateTopPadding(),
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
            )
        )
    }
}

@Composable
fun ItemDetailBody(
    itemDetails: ItemDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        ItemDetail(
            itemDetails.toItem()
        )
    }
}

@Composable
fun ItemDetail(
    item: Item,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            ItemDetailRow(
                labelResId = R.string.name,
                info = item.name
            )
            ItemDetailRow(
                labelResId = R.string.price,
                info = item.formatedPrice()
            )
            ItemDetailRow(
                labelResId = R.string.quantity_in_stock,
                info = item.quantity.toString()
            )
        }
    }
}

@Composable
fun ItemDetailRow(
    @StringRes labelResId: Int,
    info: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(stringResource(labelResId))
        Spacer(Modifier.weight(1f))
        Text(
            text = info,
            fontWeight = FontWeight.Bold
        )
    }
}





















