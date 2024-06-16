package com.example.listbuyapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listbuyapp.MainScreen.MainScreen
import com.example.listbuyapp.Screens.AddItemScreen.AddItemScreen
import com.example.listbuyapp.Screens.NoteItemScreen.NoteItemScreen
import com.example.listbuyapp.Utils.Routes


//4.34.
@Composable
fun MainNavigationGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN){

        composable(Routes.ADD_ITEM + "/{listId}"){
            AddItemScreen()
        }

        composable(Routes.NOTE_ITEM + "/{noteId}"){
            NoteItemScreen(){
                navController.popBackStack()
            }
        }

        composable(Routes.MAIN_SCREEN){
            MainScreen(navController)
        }

    }
}