package com.example.listbuyapp.Screens.SettingsScreen



//4.47.


sealed class SettingsEvent {
    data class onItemSelected(val color: String): SettingsEvent()
    data class onThemeSelected(val colorTheme: String): SettingsEvent()
}