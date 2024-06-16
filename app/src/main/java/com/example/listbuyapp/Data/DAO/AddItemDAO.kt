package com.example.listbuyapp.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.listbuyapp.Data.Entity.AddItem
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

//6. Интерфейс для AddItem

@Dao
interface AddItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: AddItem)

    @Update
    suspend fun updateItem(item: AddItem)

    @Delete
    suspend fun deleteItem(item: AddItem)

    @Query("SELECT * FROM add_item_table WHERE listId = :listId")
    fun getAllItemById(listId: Int): Flow<List<AddItem>>


    @Query("SELECT * FROM shop_list_table WHERE id = :listId")
    suspend fun getListItemById(listId: Int): ShoppingListItem
    @Update
    suspend fun updateItem(item: ShoppingListItem)


}