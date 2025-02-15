package com.example.barcode

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel

class ViewModelBarcode:ViewModel() {
    //Это главная функция. Она принимает контекст приложения и желаемое значение яркости
    fun changeScreenBrightness(context: Context, value: Int) {
        //Сначала проверяем, есть ли у приложения разрешение на запись настроек системы
        if (Settings.System.canWrite(context)) {
            //Если разрешение есть, вызывается функция для установки яркости
            setScreenBrightness(context, value)
        } else {
            //Если разрешения нет, вызывается функция для запроса разрешения у пользователя
            context.startActivity(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS))
        }
    }
    //Эта функция устанавливает яркость экрана
    private fun setScreenBrightness(context: Context, value: Int) {
        //Устанавливает режим яркости экрана в ручной режим. Это важно, потому что если режим автоматический, то ручная установка яркости будет игнорироваться
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS_MODE,
            Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
        )
        //Устанавливает фактическое значение яркости на заданное значение value
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            value
        )
    }
}