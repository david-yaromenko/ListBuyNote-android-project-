package com.example.listbuyapp.Data.Repository

import com.example.listbuyapp.Data.DAO.AddItemDAO
import com.example.listbuyapp.Data.Entity.AddItem
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.Data.Repository.AddRepository
import kotlinx.coroutines.flow.Flow


//13.


class AddRepositoryImplementation(val dao: AddItemDAO): AddRepository {
    override suspend fun insertItem(item: AddItem) {
        dao.insertItem(item)
    }

    override suspend fun updateItem(item: AddItem) {
        dao.updateItem(item)
    }

    override suspend fun deleteItem(item: AddItem) {
        dao.deleteItem(item)
    }

    override fun getAllItemById(listId: Int): Flow<List<AddItem>> {
        return dao.getAllItemById(listId)
    }

    override suspend fun getListItemById(listId: Int): ShoppingListItem {
        return dao.getListItemById(listId)
    }

    override suspend fun updateItem(item: ShoppingListItem) {
        dao.updateItem(item)
    }
}