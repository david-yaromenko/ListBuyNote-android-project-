package com.example.listbuyapp.Screens.SettingsScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listbuyapp.R


//4.48


@Composable
fun UIColorItem(
    item: ColorItem,
    onEvent: (SettingsEvent) -> Unit
) {

    IconButton(
        onClick = {
            onEvent(SettingsEvent.onItemSelected(item.color))
        },
        modifier = Modifier
            .padding(start = 10.dp)
            .clip(CircleShape)
            .size(45.dp)
            .background(color = Color(android.graphics.Color.parseColor(item.color)))
    )
    {
        if (item.isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "check", tint = Color.White
            )
        }
    }
}