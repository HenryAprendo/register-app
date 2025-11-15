package com.henrydev.registerapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrydev.registerapp.data.Item
import com.henrydev.registerapp.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val itemsRepository: ItemsRepository
): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream()
            .map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME_OUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        const val TIME_OUT_MILLIS: Long = 5_000L
    }

}

data class HomeUiState(
    val items: List<Item> = listOf()
)