package com.example.listbuyapp.Screens.NoteListScreen

import com.example.listbuyapp.Data.Entity.NoteItem


//4.42.



sealed class NoteListEvent {
    data class onShowDeleteDialog(val item: NoteItem): NoteListEvent()
    data class onItemClick(val route: String): NoteListEvent()
    data class onTextSearchChange(val text: String): NoteListEvent()
    object unDoneDeleteItem: NoteListEvent()
}