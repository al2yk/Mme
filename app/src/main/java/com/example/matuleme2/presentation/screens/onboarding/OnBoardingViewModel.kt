package com.example.matuleme2.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.presentation.screens.onboarding.model.OnBoardingUIState
import com.example.matuleme2.presentation.screens.singin.Signin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnBoardingViewModel : ViewModel() {
    //Спросить Настю, как сохранить сотояние в системе
    private val _externalOnBoardState =
        MutableStateFlow<OnBoardingUIState>(OnBoardingUIState.OnBoarding1())
    val externalOnBoardState = _externalOnBoardState.asStateFlow()


    fun clickNextOnBoarding() {
        when (_externalOnBoardState.value) {
            is OnBoardingUIState.OnBoarding1 -> _externalOnBoardState.value =
                OnBoardingUIState.OnBoarding2()

            is OnBoardingUIState.OnBoarding2 -> _externalOnBoardState.value =
                OnBoardingUIState.OnBoarding3()

            is OnBoardingUIState.OnBoarding3 ->
                {}
                //Навигация дальше

            }

        }
    }
