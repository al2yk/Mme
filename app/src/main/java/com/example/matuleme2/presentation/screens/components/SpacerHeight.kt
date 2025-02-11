package com.example.matuleme2.presentation.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerHeight(SM:Int){
    Spacer(modifier = Modifier.height(SM.dp))
}