package com.example.listbuyapp.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//3.Этот дата класс отвечает за заметки

@Entity(tableName = "note_item_table")
data class NoteItem(

    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
    val time: String,

)
