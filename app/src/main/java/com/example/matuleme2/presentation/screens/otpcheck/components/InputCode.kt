package com.example.matuleme2.presentation.screens.otpcheck.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import com.example.matuleme2.presentation.screens.otpcheck.OTPCheckViewModel

//СТРОКА ВВОДА КОДА(все 6 ячеек)
@Composable
fun InputCode(vm: OTPCheckViewModel, email: String) {
    val codeLength = 6 // Длина кода
    val code = remember { mutableStateListOf(*Array(codeLength) { "" }) }
    val focusRequesters = List(codeLength) { FocusRequester() }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                for (i in 0 until codeLength) {
                    InputView(code[i], focusRequesters[i]) { newValue ->
                        if (newValue.length <= 1) {
                            code[i] = newValue
                            if (newValue.isNotEmpty() && i < codeLength - 1) {
                                focusRequesters[i + 1].requestFocus()
                            } else if (newValue.isEmpty() && i > 0) {
                                focusRequesters[i - 1].requestFocus()
                            }
                        }
                        if (code.all { it.isNotEmpty() }) {
                            vm.checkOtpCode(email, code.joinToString(""))
                        }
                    }
                }
            }
        }
    }
}