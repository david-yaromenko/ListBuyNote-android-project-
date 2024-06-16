package com.example.listbuyapp.Data.Repository

import com.example.listbuyapp.Data.Entity.AddItem
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow


//12.

interface AddRepository {

    suspend fun insertItem(item: AddItem)

    suspend fun updateItem(item: AddItem)

    suspend fun deleteItem(item: AddItem)

    fun getAllItemById(listId: Int): Flow<List<AddItem>>


    suspend fun getListItemById(listId: Int): ShoppingListItem
    suspend fun updateItem(item: ShoppingListItem)

}