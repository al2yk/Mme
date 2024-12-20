package com.example.matuleme2.data.states

import android.location.Address
import android.provider.Telephony

data class EditProfileState(
    val name: String = "",
    val surname: String = "",
    val address: String = "",
    val telephone: String ="",
    val password: String = ""
    )