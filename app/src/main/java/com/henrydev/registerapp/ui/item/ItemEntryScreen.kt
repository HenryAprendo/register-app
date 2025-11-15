package com.henrydev.registerapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.registerapp.R
import com.henrydev.registerapp.RegisterTopAppBar
import com.henrydev.registerapp.ui.AppViewModelProvider
import com.henrydev.registerapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object ItemEntryDestination: NavigationDestination {
    override val route = "item_entry"
    override val titleRes = R.string.add_item
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: ItemEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            RegisterTopAppBar(
                title = stringResource(ItemEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertItem()
                    navigateBack()
                }
            },
            modifier = modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .fillMaxWidth()
        )
    }

}

@Composable
fun ItemEntryBody(
    itemUiState: ItemUiState,
    onItemValueChange: (ItemDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.padding(16.dp)
    ) {
        ItemInputForm(
            itemDetails = itemUiState.itemDetails,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSaveClick,
            enabled = itemUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}


@Composable
fun ItemInputForm(
    itemDetails: ItemDetails,
    onValueChange: (ItemDetails) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
    ) {
        OutlinedTextField(
            value = itemDetails.name,
            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.name)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { defaultKeyboardAction(ImeAction.Next) }
            ),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondary,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
//                disabledContainerColor = MaterialTheme.colorScheme.secondary
//            ),
            enabled = enabled,
            placeholder = { Text("item") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = itemDetails.price,
            onValueChange = { onValueChange(itemDetails.copy(price = it)) },
            label = { Text(stringResource(R.string.price)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { defaultKeyboardAction(ImeAction.Next) }
            ),
            enabled = enabled,
            singleLine = true,
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondary,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
//                disabledContainerColor = MaterialTheme.colorScheme.secondary
//            ),
            placeholder = { Text("1000") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = itemDetails.quantity,
            onValueChange = { onValueChange(itemDetails.copy(quantity = it)) },
            label = { Text(stringResource(R.string.quantity)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = { defaultKeyboardAction(ImeAction.Done) }
            ),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondary,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
//                disabledContainerColor = MaterialTheme.colorScheme.secondary
//            ),
            enabled = enabled,
            placeholder = { Text("20") },
            modifier = Modifier.fillMaxWidth()
        )
        if (!enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}


