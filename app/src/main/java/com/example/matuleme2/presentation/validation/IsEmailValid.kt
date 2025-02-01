package com.example.matuleme2.presentation.validation

import android.text.TextUtils

/** Функция, которая проверяет соответствие приходящей строки паттерну электронной почты */

// (соответствие паттерну «name@domenname.ru», где имя и доменное имя может состоять только из маленьких букв и цифр,
// старший домен только из символов количетсвом больше двух). При некорректном заполнении необходимо отобразить ошибку (диалоговое окно)
fun String.isEmailValid () : Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


