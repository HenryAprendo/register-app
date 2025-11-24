package com.henrydev.registerapp.ui.movement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.henrydev.registerapp.ui.navigation.NavigationDestination
import com.henrydev.registerapp.R
import com.henrydev.registerapp.RegisterTopAppBar
import com.henrydev.registerapp.ui.AppViewModelProvider
import kotlinx.coroutines.launch

object EntryMovementDestination: NavigationDestination {
    override val route: String = "entry_move"
    override val titleRes: Int = R.string.entry_move
    val itemIdArg = "itemId"
    val routeWithArg = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryMovementScreen(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryMovementViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    val uiState: ItemMoveUiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RegisterTopAppBar(
                title = stringResource(EntryMovementDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->

        val coroutineScope = rememberCoroutineScope()

        EntryMovementBody(
            uiState = uiState,
            onAddItem = {
                coroutineScope.launch { viewModel.addItemsToTheRegister() }
                navigateBack()
            },
            onRemoveItem = {
                coroutineScope.launch { viewModel.removeItemsToTheRegister() }
                navigateBack()
            },
            onValueChange = { input ->
                viewModel.updateQuantity(input)
            },
            modifier = modifier.padding(
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                top = innerPadding.calculateTopPadding()
            )
        )

    }
}


@Composable
fun EntryMovementBody(
    uiState: ItemMoveUiState,
    onAddItem: () -> Unit,
    onRemoveItem: () -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.move_title,uiState.item.name),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = uiState.quantityInput,
            onValueChange = { onValueChange(it) },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { defaultKeyboardAction(ImeAction.Done) }
            ),
            isError = !uiState.inputValid,
            supportingText = {
                if (!uiState.inputValid) {
                    Text("Enter a valid value")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Row(Modifier.fillMaxWidth().padding(16.dp)) {
            Button(
                onClick = onAddItem,
                enabled = uiState.inputValid
            ) {
                Text("Entry")
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = onRemoveItem,
                enabled = uiState.inputValid
            ) {
                Text("Outward")
            }
        }
    }
}































