package com.example.listbuyapp.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//1. Этот дата класс отвечает за общий список дел.

@Entity(tableName = "shop_list_table")
data class ShoppingListItem(

    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val time: String,
    val allItemsCount: Int,
    val allSelectedItemsCount: Int
)
