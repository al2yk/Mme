package com.example.matuleme2.presentation.validation

import android.text.TextUtils

/** Функция, которая проверяет соответствие приходящей строки паттерну электронной почты */
fun String.isEmailValid () : Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


