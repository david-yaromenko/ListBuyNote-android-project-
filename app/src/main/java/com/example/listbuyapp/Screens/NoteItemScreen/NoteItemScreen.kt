package com.example.listbuyapp.Screens.NoteItemScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.R
import com.example.listbuyapp.Utils.UIEvent
import com.example.listbuyapp.ui.theme.BlueLight
import com.example.listbuyapp.ui.theme.Counter
import com.example.listbuyapp.ui.theme.Edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


//2.23.

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun NoteItemScreen(
    viewModel: NoteItemViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit) {

    val snackbarHostState = remember { SnackbarHostState()}
    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect{uiEvent ->
            when(uiEvent){
                is UIEvent.PopBackStack -> {
                    onPopBackStack()
                }
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

    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
    )
    {

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor(viewModel.bgColor.value))))
        {
            Card(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp), colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(8)
            )
            {
                Column(modifier = Modifier.fillMaxWidth())
                {
                    Row(modifier = Modifier.fillMaxWidth())
                    {
                        TextField(
                            modifier = Modifier.weight(1f),
                            value = viewModel.title,
                            onValueChange = {text ->
                                viewModel.onEvent(NoteEvent.onTitleChange(text))
                            },
                            label = {
                                Text(text = "Title...", fontSize = 14.sp)
                            },
                            colors = textFieldColors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = BlueLight,
                                unfocusedIndicatorColor = BlueLight,
                                focusedLabelColor = BlueLight
                            ),
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(android.graphics.Color.parseColor(viewModel.titleColor.value)))
                        )
                        IconButton(onClick = {
                            viewModel.onEvent(NoteEvent.onSave)
                        })
                        {
                            Icon(painter = painterResource(id = R.drawable.save), contentDescription = "save", tint = BlueLight)
                        }
                    }
                    TextField(
                        value = viewModel.description,
                        onValueChange = {text ->
                            viewModel.onEvent(NoteEvent.onDescriptionChange(text))
                        },
                        label = {
                            Text(text = "Description...", fontSize = 14.sp)
                        },
                        colors = textFieldColors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = BlueLight
                        ),
                        textStyle = TextStyle(fontSize = 14.sp)
                    )
                }

            }

        }

    }
}