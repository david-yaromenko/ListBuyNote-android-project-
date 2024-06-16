package com.example.listbuyapp.Screens.ShoppingListScreen

import com.example.listbuyapp.Data.Entity.ShoppingListItem


//4.27

sealed class ShoppingListEvent {

    data class OnShowDeleteDialog(val item: ShoppingListItem): ShoppingListEvent()
    data class OnShowEditDialog(val item: ShoppingListItem): ShoppingListEvent()
    data class OnItemClick(val route: String): ShoppingListEvent()
    object OnItemSave: ShoppingListEvent()
 }