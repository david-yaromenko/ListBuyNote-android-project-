package com.example.listbuyapp.Screens.SettingsScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listbuyapp.DataStoreManager.DataStore
import com.example.listbuyapp.Screens.SettingsScreen.Theme.ColorTheme
import com.example.listbuyapp.Screens.SettingsScreen.Theme.ColorThemeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//4.49.


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    val colorItemList = mutableStateOf<List<ColorItem>>(emptyList())

    val colorTheme = mutableStateOf<List<ColorTheme>>(emptyList())

    val bgColor = mutableStateOf("#FFE0E0E0")

    init {
        viewModelScope.launch {

            dataStore.getStringPreference(
                DataStore.TITLE_COLOR,
                "#000000"
            ).collect{selectedColor ->

                val tempColorItemList = ArrayList<ColorItem>()

                ColorUtils.colorList.forEach{color ->
                    tempColorItemList.add(
                        ColorItem(
                            color,
                            selectedColor == color
                        )
                    )
                }

                colorItemList.value = tempColorItemList

            }
        }

        viewModelScope.launch {
            dataStore.getStringPreference(
                DataStore.THEME_COLOR,
                "#FFE0E0E0"
            ).collect{selectedColorTheme ->

                val tempColorThemeList = ArrayList<ColorTheme>()

                ColorThemeUtils.colorListTheme.forEach{colorTheme ->
                    tempColorThemeList.add(
                        ColorTheme(
                            colorTheme,
                            selectedColorTheme == colorTheme
                        )
                    )
                }
                colorTheme.value = tempColorThemeList
            }
        }

        //Switch theme
        viewModelScope.launch {
            dataStore.getStringPreference(
                DataStore.THEME_COLOR,
                "#FFFFFFFF"
            ).collect{color ->

                bgColor.value = color

            }
        }
    }


    fun onEvent(event: SettingsEvent){
        when(event){
            is SettingsEvent.onItemSelected ->{
                viewModelScope.launch {
                    dataStore.saveStringPreference(
                        event.color,
                        DataStore.TITLE_COLOR
                    )
                }
            }

            is SettingsEvent.onThemeSelected -> {
                viewModelScope.launch {
                    dataStore.saveStringPreference(
                        event.colorTheme,
                        DataStore.THEME_COLOR
                    )
                }
            }
        }
    }

}