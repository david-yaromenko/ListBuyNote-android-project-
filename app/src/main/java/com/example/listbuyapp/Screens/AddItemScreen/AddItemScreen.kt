package com.example.listbuyapp.Screens.AddItemScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.Dialog.MainDialog
import com.example.listbuyapp.R
import com.example.listbuyapp.Utils.UIEvent
import com.example.listbuyapp.ui.theme.BlueLight
import com.example.listbuyapp.ui.theme.BlueLight2
import com.example.listbuyapp.ui.theme.Counter
import kotlinx.coroutines.launch


//2.22



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
){

    val snackbarHostState = remember{ SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val itemsList = viewModel.itemsList?.collectAsState(initial = emptyList())


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{uiEvent ->
            when(uiEvent) {
                is UIEvent.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            uiEvent.message
                        )
                    }
                }
                else -> {}
            }
        }
    }
    
    
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }

    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor(viewModel.bgColor.value)))) {

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                colors = CardDefaults.cardColors(Color.White))
            {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically)
                {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.itemText.value,
                        onValueChange = {text ->
                                    viewModel.onEvent(AddItemEvent.OnTextChange(text))
                        },
                        label = {
                            Text(text = "New item", fontSize = 12.sp)
                        },
                        colors = textFieldColors(
                            focusedIndicatorColor = BlueLight,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedLabelColor = BlueLight

                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color(android.graphics.Color.parseColor(viewModel.titleColor.value)),
                        ),
                        singleLine = true
                    )
                    IconButton(
                        onClick = {
                            viewModel.onEvent(AddItemEvent.onItemSave)
                        }) {
                        Icon(painter = painterResource(
                            id = R.drawable.add_icon),
                            contentDescription = "add")
                    }
                }
            }

            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(top = 3.dp, start = 5.dp, end = 5.dp))
            {
                if(itemsList != null){
                    items(itemsList.value){item ->
                        UIAddItem(item = item,
                            onEvent = {event ->
                                viewModel.onEvent(event)
                        })

                    }
                }
            }
        }

        MainDialog(dialogController = viewModel)

        if (itemsList?.value?.isEmpty() == true){
            Text(text = "Empty", fontSize = 25.sp, textAlign = TextAlign.Center, color = BlueLight2,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight())
        }
    }
}