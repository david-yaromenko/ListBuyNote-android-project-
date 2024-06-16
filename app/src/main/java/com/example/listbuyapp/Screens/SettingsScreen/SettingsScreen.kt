package com.example.listbuyapp.Screens.SettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.Screens.SettingsScreen.Theme.UIColorTheme

//2.21

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {

    val listColor = viewModel.colorItemList.value

    val listThemeColor = viewModel.colorTheme.value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(android.graphics.Color.parseColor(viewModel.bgColor.value))))
    {
        Text(text = "Title color", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 16.dp, top = 20.dp))
        Text(text = "Select a title color: ", fontSize = 12.sp, color = Color.Gray,modifier = Modifier.padding(start = 16.dp))

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp))
        {
            items(listColor){item ->
                UIColorItem(item = item){event ->

                    viewModel.onEvent(event)

                }
            }
        }


        Text(text = "Theme color", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 16.dp, top = 20.dp))
        Text(text = "Select a theme color: ", fontSize = 12.sp, color = Color.Gray,modifier = Modifier.padding(start = 16.dp))

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp))
        {
            items(listThemeColor){item ->
                UIColorTheme(item = item){event ->
                    viewModel.onEvent(event)
                }
            }
        }
    }

}