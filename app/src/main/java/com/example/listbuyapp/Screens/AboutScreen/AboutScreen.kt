package com.example.listbuyapp.Screens.AboutScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.listbuyapp.R
import com.example.listbuyapp.ui.theme.BlueLight


//2.20
//Экран about

@Composable
fun AboutScreen() {

val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) 
    {
        Icon(painter = painterResource(id = R.drawable.money),
            contentDescription = "money",
            tint = BlueLight,
            modifier = Modifier.size(100.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "This app developed by DavCorp. \n" +
                "Version - 1.0.0 \n" +
                "To get more information: \n",
            textAlign = TextAlign.Center)
        Text(modifier = Modifier.padding(top = 10.dp)
            .clickable {
                uriHandler.openUri("https://www.youtube.com/") },
            text = ">>>Click Here<<<", color = BlueLight)
    }
}