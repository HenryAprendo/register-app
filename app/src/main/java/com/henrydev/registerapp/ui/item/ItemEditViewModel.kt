package com.henrydev.registerapp.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.registerapp.data.ItemsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ItemEditViewModel(
    val savedStateHandle: SavedStateHandle,
    val itemsRepository: ItemsRepository
): ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    var itemUiState: ItemUiState by mutableStateOf(ItemUiState())
        private set

    init {
        viewModelScope.launch {
            itemUiState = itemsRepository.getItemStream(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(isEntryValid = true)
        }
    }

    suspend fun updateItem() {
        if (validateInput(itemUiState.itemDetails)) {
            val item = itemUiState.itemDetails.toItem()
            itemsRepository.updateItem(item)
        }
    }

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState = ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        with(uiState) {
            return name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }

}



