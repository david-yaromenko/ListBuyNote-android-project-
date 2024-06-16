package com.example.listbuyapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.listbuyapp.MainScreen.MainScreen
import com.example.listbuyapp.Navigation.MainNavigationGraph
import com.example.listbuyapp.ui.theme.ListBuyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListBuyAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainNavigationGraph()
                }
            }
        }
    }
}