package com.example.listbuyapp.Screens.SettingsScreen

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import com.example.listbuyapp.ui.theme.Green1
import com.example.listbuyapp.ui.theme.Red1
import com.example.listbuyapp.ui.theme.Yelow1


//4.50.



object ColorUtils {
    val colorList = listOf(
        "#FF00B0FF",
        "#000000",
        "#C30000",
        "#7C4DFF",
        "#69F0AE",
        "#8E8E8E",
        "#13B700",
        "#FF6E40",
        "#EEFF41",
        "#E040FB",
        "#AED581",
        "#955050"

    )

    fun getProgressColor(progress: Float): Color{ //функция по изменению цвета индикатора прогресса по мере того сколько задач выполнено
        return when(progress){
            in 0.0..0.339 -> Red1
            in 0.34..0.669 -> Yelow1
            in 0.67..1.0 -> Green1
            else -> Red1
        }
    }
}