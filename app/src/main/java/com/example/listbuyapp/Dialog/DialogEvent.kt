package com.example.listbuyapp.Dialog


//4.30

sealed class DialogEvent {

    data class OnTextChange(val text: String): DialogEvent()
    object OnCancel: DialogEvent()
    object OnConfirm: DialogEvent()

}