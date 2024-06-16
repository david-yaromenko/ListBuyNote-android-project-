package com.example.listbuyapp.Data.Repository

import com.example.listbuyapp.Data.DAO.ShoppingListDAO
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.Data.Repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

class ShoppingListRepositoryImplementation(private val dao: ShoppingListDAO):
    ShoppingListRepository {

    override suspend fun insertItem(item: ShoppingListItem) {
        dao.insertItem(item)

    }

    override suspend fun deleteItem(item: ShoppingListItem) {
        dao.deleteShoppingList(item)

    }

    override fun getAllItems(): Flow<List<ShoppingListItem>> {
        return dao.getAllItems()

    }


}