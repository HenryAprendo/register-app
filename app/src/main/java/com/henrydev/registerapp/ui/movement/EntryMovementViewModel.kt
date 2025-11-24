package com.henrydev.registerapp.ui.movement

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.registerapp.data.Item
import com.henrydev.registerapp.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class EntryMovementViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
): ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[EntryMovementDestination.itemIdArg])

    private val _quantity = MutableStateFlow("")
    val quantity: StateFlow<String> = _quantity.asStateFlow()

    val uiState: StateFlow<ItemMoveUiState> =
        combine(
            itemsRepository.getItemStream(itemId),
            _quantity
        ) { item, qty ->
            ItemMoveUiState(
                item = item ?: defaultItem,
                quantityInput = qty,
                inputValid = (qty.toIntOrNull() ?: 0) > 0
            )
        }  .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ItemMoveUiState()
        )

    fun updateQuantity(value: String) {
        _quantity.value = value
    }

    suspend fun addItemsToTheRegister() {
        if (uiState.value.inputValid) {
            val currentItem = uiState.value.item
            val quantityToAdd = uiState.value.quantityInput.toInt()
            itemsRepository.updateItem(currentItem.copy( quantity = currentItem.quantity + quantityToAdd))
            _quantity.value = ""
        }
    }

    suspend fun removeItemsToTheRegister() {
        if (uiState.value.inputValid) {
            val currentItem = uiState.value.item
            val quantityToRemove = uiState.value.quantityInput.toInt()
            itemsRepository.updateItem(currentItem.copy( quantity = currentItem.quantity - quantityToRemove))
            _quantity.value = ""
        }
    }

}

data class ItemMoveUiState(
    val item: Item = defaultItem,
    val quantityInput: String = "",
    val inputValid: Boolean = false
)

val defaultItem: Item = Item(
    id = 0,
    name = "default_name",
    price = 0.0,
    quantity = 0
)