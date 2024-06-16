package com.example.listbuyapp.MainScreen.BottomNavigation

import com.example.listbuyapp.R
import com.example.listbuyapp.Utils.Routes


//1.17

sealed class ButtomNavigationItem(val title: String, val iconId: Int,val route: String) {

    object ListItem: ButtomNavigationItem("List", R.drawable.list_icon, Routes.SHOPPING_LIST)
    object NoteItem: ButtomNavigationItem("Note", R.drawable.note_icon, Routes.NOTE_LIST)
    object AboutItem: ButtomNavigationItem("About", R.drawable.info_icon, Routes.ABOUT)
    object SettingsItem: ButtomNavigationItem("Settings", R.drawable.settings_icon, Routes.SETTINGS)

}