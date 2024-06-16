package com.example.listbuyapp.MainScreen

import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.Screens.ShoppingListScreen.ShoppingListEvent

//4.32.

sealed class MainScreenEvent {
    object OnItemSave: MainScreenEvent()
    data class Navigate(val route: String):MainScreenEvent()
    data class NavigateMain(val route: String):MainScreenEvent()
    data class OnNewItemClick(val route: String): MainScreenEvent()

}