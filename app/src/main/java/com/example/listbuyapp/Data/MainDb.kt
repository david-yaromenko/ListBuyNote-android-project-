package com.example.listbuyapp.Data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.listbuyapp.Data.DAO.AddItemDAO
import com.example.listbuyapp.Data.DAO.NoteItemDAO
import com.example.listbuyapp.Data.DAO.ShoppingListDAO
import com.example.listbuyapp.Data.Entity.AddItem
import com.example.listbuyapp.Data.Entity.NoteItem
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.Data.Entity.Testtem


//4.создание самой БД.


@Database(
    entities = [
        ShoppingListItem::class,
        AddItem::class,
        NoteItem::class,
        Testtem::class], //Testtem - класс для теста автомиграции
    autoMigrations = [AutoMigration(from = 1, to = 2, spec = MainDb.RenameTableTest::class)],
    version = 2,
    exportSchema = true
)
abstract class MainDb : RoomDatabase(){

    @RenameTable(fromTableName = "test_item_table", toTableName = "test_table")
    class RenameTableTest: AutoMigrationSpec

    abstract val shoppingListDAO: ShoppingListDAO
    abstract val addItemDAO: AddItemDAO
    abstract val noteItemDAO: NoteItemDAO

}
