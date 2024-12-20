package com.example.matuleme2.data.states

import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.CheckBox

data class SignUpState (
    val name:String = "",
    val email: String = "",
    val password: String = "",
    val checkBox: Boolean = false
)