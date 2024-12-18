package com.example.matuleme2.presentation.screens.components

import androidx.compose.ui.graphics.Brush
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.disable

object gradient {
    val gradient = Brush.verticalGradient(
        0.0f to accent,
        1.0f to disable,
        startY = 0.0f,
        endY = 4000.0f
    )

}