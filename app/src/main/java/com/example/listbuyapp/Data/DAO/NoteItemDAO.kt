package com.example.listbuyapp.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.listbuyapp.Data.Entity.NoteItem
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

//7. Интерфейс для блокнота

@Dao
interface NoteItemDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: NoteItem)

    @Update
    suspend fun updateItem(item: NoteItem)

    @Delete
    suspend fun deleteItem(item: NoteItem)

    @Query("SELECT * FROM note_item_table")
    fun getAllItem(): Flow<List<NoteItem>>

    @Query("SELECT * FROM note_item_table WHERE id = :id")
    suspend fun getNoteItemById(id: Int): NoteItem
}
