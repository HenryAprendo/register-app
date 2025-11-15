package com.henrydev.registerapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.registerapp.R
import com.henrydev.registerapp.RegisterTopAppBar
import com.henrydev.registerapp.data.Item
import com.henrydev.registerapp.ui.AppViewModelProvider
import com.henrydev.registerapp.ui.item.formatedPrice
import com.henrydev.registerapp.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.register
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RegisterTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add an item"
                )
            }
        },
    ) { innerPadding ->

        val homeUiState by viewModel.homeUiState.collectAsState()

        HomeBody(
            itemList = homeUiState.items,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding
        )

    }
}

@Composable
fun HomeBody(
    itemList: List<Item>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = "No items in the database",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(contentPadding)
            )
        } else {
            RegisterList(
                itemList = itemList,
                contentPadding = contentPadding,
            )
        }
    }
}

@Composable
fun RegisterList(
    itemList: List<Item>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(horizontal = 16.dp),
        contentPadding = contentPadding
    ) {
        items(itemList, { it.id }) { itemDetail ->
            RegisterItem(
                item = itemDetail,
                modifier = Modifier.clickable {

                }
            )
        }
    }
}

@Composable
fun RegisterItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = item.formatedPrice(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = stringResource(R.string.in_stock, item.quantity),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}












