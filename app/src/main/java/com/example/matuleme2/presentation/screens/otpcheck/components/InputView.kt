package com.example.matuleme2.presentation.screens.otpcheck.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.red
import com.example.matuleme2.presentation.ui.theme.text
import kotlinx.coroutines.delay

//ОДНА ЯЧЕЙКА Для ввода OTP
@Composable
fun InputView(ColorBorder:Color,value: String,focusRequester: FocusRequester,onValue: (String) -> Unit){
    /* BasicTextField(
         readOnly = false, value = value, onValueChange = onValueChange,
         modifier = Modifier
             .padding(end = 12.dp)

             .focusRequester(focusRequester)
             .wrapContentSize(),
         maxLines = 1,
         decorationBox = { innerTextField ->
             Box(
                 contentAlignment = Alignment.Center,
                 modifier = Modifier
                     .width(46.dp)
                     .height(99.dp)
                     .clip(RoundedCornerShape(12.dp))
                     .background(background),
             )
             {
                 innerTextField()
             }
         },
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = null
        )
    )*/

    Box(
        modifier = Modifier
            .width(58.dp)
            .height(99.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onValue(it)},
            singleLine = true,
            maxLines = 1, shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .width(46.dp)
                .height(99.dp)
                .focusRequester(focusRequester),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = ColorBorder,
                unfocusedContainerColor = background,
                unfocusedTextColor = text,
                focusedContainerColor = background,
                focusedTextColor = text,
                focusedBorderColor = red
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = text
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            /*            keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = null
                        )*/
        )
    }
}

/*@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContentView(
    textList: List<MutableState<TextFieldValue>>,
    requesteList: List<FocusRequester>,
    viewModel: OTPCheckViewModel,
    controller: NavHostController
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Row(modifier = Modifier.align(Alignment.Center)) {
                for (i in textList.indices) {
                    InputView(
                        value = textList[i].value,
                        focusRequester = requesteList[i],
                        onValue = {newValue ->

                            if(textList[i].value.text != ""){
                                if (newValue.text ==""){
                                    textList[i].value = TextFieldValue(
                                        text = "",
                                        selection = TextRange(0)
                                    )
                                }
                                return@InputView
                            }
                            textList[i].value = TextFieldValue(
                                text = newValue.text,
                                selection = TextRange(newValue.text.length)
                            )
                            NextFocus(textList,requesteList)
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = null, block = {
        delay(300)
        requesteList[0].requestFocus()
    })
}

fun correctOtp(textList: List<MutableState<TextFieldValue>>, onVerifyCode:((success:Boolean)->Unit)?=null){
    var code = ""
    for (text in textList){
        code += text.value.text
    }

    if (code.length == 6) {

    }
}
*/