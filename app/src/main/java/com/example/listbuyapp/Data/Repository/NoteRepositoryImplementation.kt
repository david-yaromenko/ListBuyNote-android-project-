package com.example.listbuyapp.Data.Repository

import com.example.listbuyapp.Data.Entity.NoteItem
import com.example.listbuyapp.Data.DAO.NoteItemDAO
import kotlinx.coroutines.flow.Flow


//11.

class NoteRepositoryImplementation(val dao: NoteItemDAO): NoteRepository {

    override suspend fun insertItem(item: NoteItem) {
        dao.insertItem(item)
    }

    override suspend fun updateItem(item: NoteItem) {
        dao.updateItem(item)
    }

    override suspend fun deleteItem(item: NoteItem) {
        dao.deleteItem(item)
    }

    override fun getAllItem(): Flow<List<NoteItem>> {
        return dao.getAllItem()
    }

    override suspend fun getNoteItemById(id: Int): NoteItem {
        return dao.getNoteItemById(id)
    }

}