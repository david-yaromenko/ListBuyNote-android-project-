package com.example.listbuyapp.Dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.Screens.ShoppingListScreen.ShoppingListViewModel
import com.example.listbuyapp.ui.theme.BlueLight


//4.31.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDialog(dialogController: DialogController,
               viewModel: ShoppingListViewModel = hiltViewModel()) {

    if (dialogController.openDialog.value) {

        AlertDialog(

            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            title = null,
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = dialogController.dialogTitle.value,
                        style = TextStyle(color = Color.DarkGray),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    if (dialogController.showEditableText.component1())
                        TextField(
                            value = dialogController.editableText.value,
                            onValueChange = {
                                dialogController.onDialogEvent(DialogEvent.OnTextChange(it))
                            },
                            label = {
                                Text(text = "List name: ")
                            },
                            colors = textFieldColors(
                                focusedTextColor = Color(android.graphics.Color.parseColor(viewModel.titleColor.value)),
                                unfocusedTextColor = Color(android.graphics.Color.parseColor(viewModel.titleColor.value)),
                                focusedLabelColor = BlueLight,
                                focusedIndicatorColor = BlueLight,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,


                            ),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            )


                }
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogController.onDialogEvent(DialogEvent.OnConfirm)
                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogController.onDialogEvent(DialogEvent.OnCancel)
                }) {
                    Text(text = "Cancel")
                }
            }
        )

    }

}