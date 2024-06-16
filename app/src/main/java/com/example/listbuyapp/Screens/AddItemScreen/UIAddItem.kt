package com.example.listbuyapp.Screens.AddItemScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.Data.Entity.AddItem
import com.example.listbuyapp.R
import com.example.listbuyapp.ui.theme.BlueLight


//4.37.


@Composable
fun UIAddItem(
    item: AddItem,
    onEvent: (AddItemEvent) -> Unit,
    viewModel: AddItemViewModel = hiltViewModel()
    ) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)
        .clickable {
            onEvent(AddItemEvent.OnShowEditDialog(item))
        },colors = CardDefaults.cardColors(Color.White))
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
        {
            Text(text = item.name,
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
                color = Color(android.graphics.Color.parseColor(viewModel.titleColor.value)))
            Checkbox(
                checked = item.isChecked,
                onCheckedChange = { isChecked ->
                    onEvent(AddItemEvent.OnCheckedChange(item.copy(isChecked = isChecked)))
                },
                colors = CheckboxDefaults.colors(BlueLight)
            )
            IconButton(
                onClick = {
                    onEvent(AddItemEvent.OnDelete(item))
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.deletebutton), contentDescription = "delete")
            }
        }
    }
}