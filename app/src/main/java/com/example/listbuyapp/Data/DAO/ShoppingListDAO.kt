package com.example.listbuyapp.Data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow


//5.Создаем для каждого entity интерфейс DAO чтобы можно было взаимодействовать с БД


@Dao
interface ShoppingListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItem)

    @Update
    suspend fun updateItem(item: ShoppingListItem)

    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

    @Query("SELECT * FROM shop_list_table")
    fun getAllItems(): Flow<List<ShoppingListItem>>

    //УДАЛЕНИЕ СПИСКА И ЕГО ЕЛЕМЕНТОВ
    @Query("DELETE FROM add_item_table WHERE listId = :listId")
    suspend fun deleteAddItems(listId: Int)
    @Transaction
    suspend fun deleteShoppingList(item: ShoppingListItem){
        deleteAddItems(item.id!!)
        deleteItem(item)
    }

}