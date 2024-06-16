package com.example.listbuyapp.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


//2.

@Entity(tableName = "add_item_table")
data class AddItem(

    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val isChecked: Boolean,
    val listId: Int,


)
