package com.henrydev.registerapp.ui.item

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.registerapp.data.Item
import com.henrydev.registerapp.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ItemDetailViewModel(
    val savedStateHandle: SavedStateHandle,
    val itemsRepository: ItemsRepository
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailDestination.itemIdArg])

    var uiState: StateFlow<ItemDetailUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .map { item ->
                ItemDetailUiState(item.toItemDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ItemDetailUiState()
            )

    suspend fun deleteItem() {
        itemsRepository.deleteItem(uiState.value.itemDetails.toItem())
    }

    suspend fun addItemsToTheRegister(quantity: Int) {
        val item = uiState.value.itemDetails.toItem()
        itemsRepository.updateItem(item.copy( quantity = item.quantity + quantity))
    }

    suspend fun removeItemsToTheRegister(quantity: Int) {
        val item = uiState.value.itemDetails.toItem()
        itemsRepository.updateItem(item.copy(quantity = item.quantity - quantity))
    }

}


data class ItemDetailUiState(
    val itemDetails: ItemDetails = ItemDetails()
)









