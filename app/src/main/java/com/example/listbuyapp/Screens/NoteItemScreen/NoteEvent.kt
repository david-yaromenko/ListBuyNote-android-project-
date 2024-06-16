package com.example.listbuyapp.Screens.NoteItemScreen




//4.40.


sealed class NoteEvent {
    data class onTitleChange(val title: String): NoteEvent()
    data class onDescriptionChange(val description: String): NoteEvent()
    object onSave: NoteEvent()
}