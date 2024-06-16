package com.example.listbuyapp.MainScreen.BottomNavigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.listbuyapp.R
import com.example.listbuyapp.ui.theme.BlueLight


//1.18


@Composable
fun BottomNav(currentRoute: String?,
              onNavigate: (String) -> Unit
              ) {

    val listItems =
        listOf(
            ButtomNavigationItem.ListItem,
            ButtomNavigationItem.NoteItem,
            ButtomNavigationItem.AboutItem,
            ButtomNavigationItem.SettingsItem
        )

    NavigationBar(containerColor = Color.White) {

        listItems.forEach { bottomNavItem ->

            NavigationBarItem(
                selected = currentRoute == bottomNavItem.route,
                onClick = {
                          onNavigate(bottomNavItem.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavItem.iconId),
                        contentDescription = "icon"
                    )
                },
                label = {
                    Text(text = bottomNavItem.title)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.blue_light),
                    selectedTextColor = BlueLight,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                ),
                alwaysShowLabel = false
            )
        }
    }
}