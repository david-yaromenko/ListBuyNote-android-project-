package com.example.listbuyapp.Data.Repository


import com.example.listbuyapp.Data.Entity.NoteItem
import kotlinx.coroutines.flow.Flow


//10.

interface NoteRepository {

    suspend fun insertItem(item: NoteItem)

    suspend fun updateItem(item: NoteItem)

    suspend fun deleteItem(item: NoteItem)

    fun getAllItem(): Flow<List<NoteItem>>

    suspend fun getNoteItemById(id: Int): NoteItem

}