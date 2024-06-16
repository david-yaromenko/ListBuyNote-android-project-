package com.example.listbuyapp.Screens.AddItemScreen

import com.example.listbuyapp.Data.Entity.AddItem


//4.36.

sealed class AddItemEvent {

    data class OnDelete(val item: AddItem): AddItemEvent()
    data class OnShowEditDialog(val item: AddItem): AddItemEvent()
    data class OnTextChange(val text: String): AddItemEvent()
    data class OnCheckedChange(val item: AddItem): AddItemEvent()
    object onItemSave: AddItemEvent()
}