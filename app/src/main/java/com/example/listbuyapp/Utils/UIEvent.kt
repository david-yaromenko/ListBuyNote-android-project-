package com.example.listbuyapp.Utils


//4.30.

sealed class UIEvent {

    object PopBackStack: UIEvent()
    data class Navigate(val route: String): UIEvent()
    data class NavigateMain(val route: String): UIEvent()
    data class ShowSnackBar(val message: String): UIEvent()
}