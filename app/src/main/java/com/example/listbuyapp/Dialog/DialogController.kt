package com.example.listbuyapp.Dialog

import androidx.compose.runtime.MutableState


//4.29.

interface DialogController {
    val dialogTitle: MutableState<String>
    val editableText: MutableState<String>
    val openDialog: MutableState<Boolean>
    val showEditableText: MutableState<Boolean>
    fun onDialogEvent(event: DialogEvent)
}