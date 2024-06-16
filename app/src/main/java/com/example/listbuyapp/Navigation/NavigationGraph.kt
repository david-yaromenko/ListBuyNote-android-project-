package com.example.listbuyapp.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listbuyapp.Screens.AboutScreen.AboutScreen
import com.example.listbuyapp.Screens.NoteListScreen.NoteListScreen
import com.example.listbuyapp.Screens.SettingsScreen.SettingsScreen
import com.example.listbuyapp.Screens.ShoppingListScreen.ShoppingListScreen
import com.example.listbuyapp.Utils.Routes


//3.25

@Composable
fun NavigationGraph(
    navController: NavHostController,
    onNavigate: (String) -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Routes.SHOPPING_LIST
    ) {

        composable(Routes.SHOPPING_LIST) {
            ShoppingListScreen() { route ->
                onNavigate(route)
            }
        }

        composable(Routes.NOTE_LIST) {
            NoteListScreen(){route ->
                onNavigate(route)
            }
        }

        composable(Routes.ABOUT) {
            AboutScreen()
        }

        composable(Routes.SETTINGS) {
            SettingsScreen()
        }


    }
}