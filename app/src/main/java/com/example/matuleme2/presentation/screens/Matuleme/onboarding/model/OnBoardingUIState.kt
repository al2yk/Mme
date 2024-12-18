package com.example.matuleme2.presentation.screens.Matuleme.onboarding.model

import android.annotation.SuppressLint
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.matuleme2.R

@SuppressLint("SupportAnnotationUsage")
sealed interface OnBoardingUIState {

    @get:IntegerRes
    val step: Int

    //наименование
    @get:StringRes
    val titleResId: String

    //Снизу текст
    @get:StringRes
    val subtitleResId: String?

    //Кнопка
    @get:StringRes
    val btnTitleResId: String?

    //Изображение
    @get:IntegerRes
    val iconId: Int

    //Иконка
    @get:IntegerRes
    val icon2Id: Int

    data class OnBoarding1(
        override val titleResId: String = "ДОБРО ПОЖАЛОВАТЬ",
        override val subtitleResId: String? = null,
        override val step: Int = 1,
        override val iconId: Int = R.drawable.b,
        override val icon2Id: Int = R.drawable.groupwel1,
        override val btnTitleResId: String? = "Начать",
    ) : OnBoardingUIState

    data class OnBoarding2(
        override val titleResId: String = "Начнем путешествие",
        override val subtitleResId: String? = "Умная, великолепная и модная коллекция Изучите сейчас",
        override val step: Int = 2,
        override val iconId: Int = R.drawable.wel2, override val btnTitleResId: String = "Далее",
        override val icon2Id: Int = R.drawable.group2
    ) : OnBoardingUIState

    data class OnBoarding3(
        override val titleResId: String = "У Вас Есть Сила, Чтобы",
        override val subtitleResId: String? = "В вашей комнате много красивых и привлекательных растений",
        override val step: Int = 3,
        override val iconId: Int = R.drawable.boots3, override val btnTitleResId: String? = "Далее",
        override val icon2Id: Int = R.drawable.smile
    ) : OnBoardingUIState
}