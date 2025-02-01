package com.example.matuleme2.data.states

import android.graphics.Bitmap
import android.location.Address
import android.provider.Telephony
import androidx.compose.ui.graphics.ImageBitmap

data class EditProfileState(
    val name: String = "",
    val surname: String = "",
    val address: String = "",
    val telephone: String ="",
    val password: String = "",
    var codeImg: ImageBitmap? = null
    )