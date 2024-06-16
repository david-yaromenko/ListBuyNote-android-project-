package com.example.listbuyapp.MainScreen

import android.annotation.SuppressLint
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.listbuyapp.Dialog.MainDialog
import com.example.listbuyapp.MainScreen.BottomNavigation.BottomNav
import com.example.listbuyapp.Navigation.NavigationGraph
import com.example.listbuyapp.R
import com.example.listbuyapp.Utils.Routes
import com.example.listbuyapp.Utils.UIEvent
import kotlinx.coroutines.MainScope


//1.16.главный экран

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainHostController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{uiEvent ->
            when(uiEvent){
                is UIEvent.NavigateMain -> {
                    mainHostController.navigate(uiEvent.route)
                }

                is UIEvent.Navigate -> {
                    navController.navigate(uiEvent.route)
                }
                else ->{}
            }
        }
    }
    
    Scaffold(
        bottomBar = {
            BottomNav(currentRoute){route ->
                viewModel.onEvent(MainScreenEvent.Navigate(route))
            }
        },
        floatingActionButton = {
           if(viewModel.showFloatingButton.value) FloatingActionButton(
                onClick = {
                    viewModel.onEvent(MainScreenEvent.OnNewItemClick(currentRoute ?: Routes.SHOPPING_LIST))
                },
                containerColor = colorResource(id = R.color.blue_light),

            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "add",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center

    )
    {
        NavigationGraph(navController){route ->
            viewModel.onEvent(MainScreenEvent.NavigateMain(route))

        }
        MainDialog(dialogController = viewModel)
    }
}