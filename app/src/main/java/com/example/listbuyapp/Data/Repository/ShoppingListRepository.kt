package com.example.listbuyapp.Data.Repository

import com.example.listbuyapp.Data.Entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

//8.

interface ShoppingListRepository {

    suspend fun insertItem(item: ShoppingListItem)

    suspend fun deleteItem(item: ShoppingListItem)

    fun getAllItems(): Flow<List<ShoppingListItem>>

}