package com.henrydev.registerapp.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

}