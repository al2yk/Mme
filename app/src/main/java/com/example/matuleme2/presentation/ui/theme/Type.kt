package com.example.matuleme2.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme2.R

val textfam =FontFamily(
    Font(R.font.raleway_italic, FontWeight.Light),
    Font(R.font.raleway_bold,FontWeight.Bold),
    Font(R.font.raleway_black,FontWeight.ExtraBold),
    Font(R.font.poppins_regular,FontWeight.Normal),
    Font(R.font.poppins_medium,FontWeight.Medium),
    Font(R.font.raleway_vedium,FontWeight.W600),
    Font(R.font.raleway_semibold,FontWeight.W800),
    Font(R.font.raleway_light,FontWeight.ExtraLight)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.raleway_italic, FontWeight.Light),
            Font(R.font.raleway_bold,FontWeight.Bold),
            Font(R.font.raleway_black,FontWeight.ExtraBold),
            Font(R.font.poppins_regular,FontWeight.Normal),
            Font(R.font.poppins_medium,FontWeight.Medium),
            Font(R.font.raleway_vedium,FontWeight.W800),

        ),
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodySmall = TextStyle(
        fontFamily = textfam

    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)