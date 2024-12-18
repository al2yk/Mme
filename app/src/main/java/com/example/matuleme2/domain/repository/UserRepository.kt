package com.example.matuleme2.domain.repository

import android.content.Context
import android.content.SharedPreferences

object UserRepository {
    private lateinit var actSystem: SharedPreferences

    fun init(context: Context) {
        actSystem = context.getSharedPreferences("actSystem", Context.MODE_PRIVATE)
    }
    //для понимания где находиться пользователь
    //просмотрели ли он onboarding
    var act: Int
        get() = actSystem.getInt("act", 0)
        set(value) = actSystem.edit().putInt("act", value).apply()

    // uuid текущий пользователь
    var uuidCurrentUser: String
        get() = actSystem.getString("current user uuid", "")!!
        set(value) = actSystem.edit().putString("current user uuid", value).apply()
    }