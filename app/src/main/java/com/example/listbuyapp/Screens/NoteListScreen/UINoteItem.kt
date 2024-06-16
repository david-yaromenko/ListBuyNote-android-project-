package com.example.listbuyapp.Screens.NoteListScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listbuyapp.Data.Entity.NoteItem
import com.example.listbuyapp.R
import com.example.listbuyapp.Utils.Routes
import com.example.listbuyapp.ui.theme.BlueLight
import com.example.listbuyapp.ui.theme.Delete


//4.38.


@Composable
fun UINoteItem(
    titleColor: String,
    item: NoteItem,
    onEvent: (NoteListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, top = 3.dp, end = 3.dp)
            .clickable {
                onEvent(
                    NoteListEvent.onItemClick(
                        Routes.NOTE_ITEM + "/${item.id}"
                    )
                )
            },
        colors = CardDefaults.cardColors(Color.White)
    )
    {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .weight(1f),
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor(titleColor))
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                    text = item.time, color = BlueLight, fontSize = 12.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth())
            {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 5.dp, bottom = 10.dp)
                        .weight(1f),
                    text = item.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray
                )
                IconButton(onClick = {
                    onEvent(NoteListEvent.onShowDeleteDialog(item))
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.deletebutton),
                        contentDescription = "delete",
                        tint = Delete,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }
            }
        }
    }
}