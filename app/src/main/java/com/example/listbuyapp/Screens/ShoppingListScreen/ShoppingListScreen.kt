package com.example.listbuyapp.Screens.ShoppingListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.Dialog.MainDialog
import com.example.listbuyapp.Utils.UIEvent
import com.example.listbuyapp.ui.theme.BlueLight
import com.example.listbuyapp.ui.theme.BlueLight2
import com.example.listbuyapp.ui.theme.Counter

//2.18
@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {

    val itemsList =
        viewModel.list.collectAsState(initial = emptyList())


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UIEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                else -> {}
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor(viewModel.bgColor.value))),
        contentPadding = PaddingValues(bottom = 100.dp)
    )
    {
        items(itemsList.value) { item ->

            UIShoppingListItem(viewModel,item) { event ->
                viewModel.onEvent(event)
            }
        }
    }

    MainDialog(dialogController = viewModel)
    if (itemsList.value.isEmpty() == true){
        Text(text = "Empty", fontSize = 25.sp, textAlign = TextAlign.Center, color = BlueLight2,
            modifier = Modifier.fillMaxSize().wrapContentHeight())
    }
}