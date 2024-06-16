package com.example.listbuyapp.Screens.ShoppingListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listbuyapp.Data.Entity.ShoppingListItem
import com.example.listbuyapp.R
import com.example.listbuyapp.Screens.SettingsScreen.ColorUtils
import com.example.listbuyapp.Utils.LiniarProgress
import com.example.listbuyapp.Utils.Routes
import com.example.listbuyapp.ui.theme.BlueLight
import com.example.listbuyapp.ui.theme.Counter
import com.example.listbuyapp.ui.theme.Delete
import com.example.listbuyapp.ui.theme.Edit

//4.26



@Composable
fun UIShoppingListItem(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    item: ShoppingListItem, onEvent: (ShoppingListEvent) -> Unit
) {

   val progress = LiniarProgress.getProgress(item.allItemsCount, item.allSelectedItemsCount)

    ConstraintLayout(
        modifier = Modifier
            .padding(start = 3.dp, end = 3.dp, top = 20.dp)
    )
    {
        val (card, deleteButton, editButton, counter) = createRefs()

        Card(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(card) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .clickable {
                onEvent(ShoppingListEvent.OnItemClick(
                    Routes.ADD_ITEM + "/${item.id}"
                ))
            },
            colors = CardDefaults.cardColors(Color.White)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            {
                Text(
                    text = item.name,
                    style = TextStyle(
                        color = Color(android.graphics.Color.parseColor(viewModel.titleColor.value)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )

                Text(
                    text = item.time,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                )

                LinearProgressIndicator(

                    progress = progress,
                    color = ColorUtils.getProgressColor(progress),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                )
            }
        }
        IconButton(onClick = {
            onEvent(ShoppingListEvent.OnShowDeleteDialog(item))
        }, modifier = Modifier
            .constrainAs(deleteButton) {
                top.linkTo(card.top)
                bottom.linkTo(card.top)
                end.linkTo(card.end)
            }
            .padding(end = 10.dp)
            .size(30.dp))
        {
            Icon(
                painter = painterResource(id = R.drawable.deletebutton),
                contentDescription = "delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Delete)
                    .padding(5.dp),
                tint = Color.White
            )
        }
        IconButton(onClick = {

            onEvent(ShoppingListEvent.OnShowEditDialog(item))

        }, modifier = Modifier
            .constrainAs(editButton) {
                top.linkTo(card.top)
                bottom.linkTo(card.top)
                end.linkTo(deleteButton.start)
            }
            .padding(end = 5.dp)
            .size(30.dp))
        {
            Icon(
                painter = painterResource(id = R.drawable.editbutton),
                contentDescription = "edit",
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Edit)
                    .padding(5.dp),
                tint = Color.White
            )
        }

        Card(shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .constrainAs(counter) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(editButton.start)
                }
                .padding(end = 5.dp)
        ) {
            Text(
                text = "${item.allItemsCount}/${item.allSelectedItemsCount}",
                modifier = Modifier
                    .background(BlueLight)
                    .padding(top = 6.dp, bottom = 6.dp, start = 6.dp, end = 6.dp),
                style = TextStyle(color = Color.White)
            )
        }
    }
}