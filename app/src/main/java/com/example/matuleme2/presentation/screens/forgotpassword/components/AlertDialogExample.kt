package com.example.matuleme2.presentation.screens.forgotpassword.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.matuleme2.R
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.textfam

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: Painter,
) {
    AlertDialog(
        icon = {
            Box(modifier = Modifier
                .clip(CircleShape)
                .size(44.dp)
                .background(accent)
                .clickable {

                })
            {
                Icon(
                    painter = painterResource(R.drawable.email),
                    tint = Color.White,
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        title = {
            Text(
                text = dialogTitle,
                color = Color.Black,
                fontFamily = textfam,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = dialogText, color = Color.Gray,
                fontFamily = textfam,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        shape = RoundedCornerShape(15.dp),

        confirmButton = {},
        dismissButton = {}
    )
}