package com.henrydev.registerapp.data

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Item)

    /**
     * Retrieve all the items for the given data source
     */
    fun getAllItemsStream(): Flow<List<Item>>

}