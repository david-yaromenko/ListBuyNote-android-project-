package com.example.listbuyapp.Data.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//этот дата класс нужен для теста миграции

@Entity(tableName = "test_table")
data class Testtem(

    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
    val time: String,


    @ColumnInfo(defaultValue = "0")
    val test: Int = 0

)
