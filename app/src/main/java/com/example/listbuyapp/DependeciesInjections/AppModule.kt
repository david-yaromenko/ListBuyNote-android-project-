package com.example.listbuyapp.DependeciesInjections

import android.app.Application
import androidx.room.Room
import com.example.listbuyapp.Data.MainDb
import com.example.listbuyapp.Data.Repository.AddRepository
import com.example.listbuyapp.Data.Repository.AddRepositoryImplementation
import com.example.listbuyapp.Data.Repository.NoteRepository
import com.example.listbuyapp.Data.Repository.NoteRepositoryImplementation
import com.example.listbuyapp.Data.Repository.ShoppingListRepository
import com.example.listbuyapp.Data.Repository.ShoppingListRepositoryImplementation
import com.example.listbuyapp.DataStoreManager.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//14. Создаем модуль Dagger Hilt


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb{
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "shop_list_db").build()
    }

    @Provides
    @Singleton
    fun provideShoppingListRepository(db: MainDb): ShoppingListRepository{
        return ShoppingListRepositoryImplementation(db.shoppingListDAO)
    }

    @Provides
    @Singleton
    fun provideAddRepository(db: MainDb): AddRepository{
        return AddRepositoryImplementation(db.addItemDAO)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: MainDb): NoteRepository{
        return NoteRepositoryImplementation(db.noteItemDAO)
    }

    @Provides
    @Singleton
    fun provideDataStore(app: Application): DataStore{
        return DataStore(app)
    }


}