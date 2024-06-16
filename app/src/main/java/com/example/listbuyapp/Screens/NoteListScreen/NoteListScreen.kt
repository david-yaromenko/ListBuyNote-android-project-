package com.example.listbuyapp.Screens.NoteListScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.Dialog.MainDialog
import com.example.listbuyapp.Screens.ShoppingListScreen.UIShoppingListItem
import com.example.listbuyapp.Utils.UIEvent
import com.example.listbuyapp.ui.theme.BlueLight
import com.example.listbuyapp.ui.theme.BlueLight2
import com.example.listbuyapp.ui.theme.Counter
import kotlinx.coroutines.launch

//2.19
//Экран для заметок

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val itemsList =
        viewModel.noteList.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UIEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                is UIEvent.ShowSnackBar -> {
                    scope.launch {
                        val result =
                            snackbarHostState.showSnackbar(
                                uiEvent.message,
                                actionLabel = "Undone",
                                duration = SnackbarDuration.Short
                            )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(NoteListEvent.unDoneDeleteItem)
                        }
                    }
                }

                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->

                Snackbar(
                    snackbarData = data,
                    modifier = Modifier.padding(bottom = 150.dp),
                )

            }
        }
    )

    {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(android.graphics.Color.parseColor(viewModel.bgColor.value)))

        )
        {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(15.dp)
            )
            {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.searchText.value,
                    onValueChange = { text ->
                        viewModel.onEvent(NoteListEvent.onTextSearchChange(text))
                    },
                    colors = textFieldColors(
                        focusedIndicatorColor = BlueLight,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = BlueLight
                    ),
                    label = {
                        Text(text = "Search...")
                    },
                )
            }
            if (viewModel.noteList2.value.isEmpty()) {
                Text(
                    text = "Empty",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = BlueLight2,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .padding(bottom = 66.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            )
            {
                items(viewModel.noteList2.value) { item ->
                    UINoteItem(viewModel.titleColor.value,item){event ->
                         viewModel.onEvent(event)
                     }
                }
            }
            MainDialog(dialogController = viewModel)
        }
    }
}