package com.example.matuleme2.presentation.screens.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matuleme2.data.models.Notification
import com.example.matuleme2.presentation.screens.notification.NotificationViewModel
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.subtextdark
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//ПЕРЕДЕЛАТЬ
//Банер В уведомлениях
@SuppressLint("SimpleDateFormat")
@Composable
fun Banner(Noti:Notification) {

    val now = LocalDateTime.now()

    // Форматирование даты и времени
    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy, ")
    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    // Преобразование даты и времени в строки
    val formattedDate = Noti.date.format(dateFormat)
    val formattedTime = Noti.date.format(timeFormat)


    @Composable
    fun FormattedTimestampDisplay(timestamp: String):String {
        // Преобразуем строку в LocalDateTime
        val localDateTime = LocalDateTime.parse(timestamp)

        // Форматируем LocalDateTime в строку
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm")
        val formattedTimestamp = localDateTime.format(formatter)

        return formattedTimestamp
    }
    val dateNotification = FormattedTimestampDisplay(Noti.date)


    val vm = viewModel { (NotificationViewModel()) }
    val state = vm.state

    LaunchedEffect(Unit) {
        vm.getNotification()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(background)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(Noti.title, fontFamily = textfam, fontWeight = FontWeight.Bold, color = text)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = Noti.description,
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                fontFamily = textfam,
                softWrap = true,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text= "$dateNotification",
                    fontSize = 12.sp, fontFamily = textfam,
                    fontWeight = FontWeight.W600, color = subtextdark
                )
               /* Text(
                    formattedTime, fontSize = 12.sp, fontFamily = textfam,
                    fontWeight = FontWeight.W600, color = subtextdark
                )*/
            }

        }
    }
}