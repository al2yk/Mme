package com.example.matuleme2.presentation.screens.checkout

import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.R
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.components.ButtonExit
import com.example.matuleme2.presentation.screens.components.MapScreen
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.ui.theme.MATULEme2Theme
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.goluboi
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam


@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CheckOutView(
    controller: NavHostController,
    location: Location?,
) {

    val vm = viewModel { CheckOutViewModel() }

    var state = vm.state
    var back = remember { mutableStateOf(0.dp) }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 60.dp)
                .blur(radius =back.value)
        ) {


            TopBar(controller, "Корзина", FontWeight.SemiBold) {}
            Spacer(modifier = Modifier.height(46.dp))

            //белый контейнер
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(block),
                contentAlignment = Alignment.TopStart
            ) {

                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {

                    Text(
                        "Контактная информация",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = text
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    //почта
                    Box(modifier = Modifier.fillMaxWidth()) {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(background),
                            contentAlignment = Alignment.Center
                            /*.clip(RoundedCornerShape(12.dp))*/
                        )
                        {
                            Icon(
                                painter = painterResource(R.drawable.emailbox),
                                contentDescription = "",
                                tint = text,
                                modifier = Modifier.size(20.dp)
                            )
                        }


                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 52.dp)
                        ) {
                            Text(
                                "ПОЧТА ПОЛЬЗОВАТЕЛЯ", color = text,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                "Email",
                                color = hint,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(11.dp))


                        Icon(
                            painter = painterResource(R.drawable.pen),
                            contentDescription = "",
                            tint = hint, modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterEnd)
                        )


                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    //телефон
                    Box(modifier = Modifier.fillMaxWidth()) {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(background),
                            contentAlignment = Alignment.Center
                            /*.clip(RoundedCornerShape(12.dp))*/
                        )
                        {
                            Icon(
                                painter = painterResource(R.drawable.phone),
                                contentDescription = "",
                                tint = text,
                                modifier = Modifier.size(20.dp)
                            )
                        }


                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 52.dp)
                        )
                        {
                            Text(
                                "ТЕЛЕФОн ПОЛЬЗОВАТЕЛЯ", color = text,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                "Телефон",
                                color = hint,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(11.dp))


                        Icon(
                            painter = painterResource(R.drawable.pen),
                            contentDescription = "",
                            tint = hint, modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterEnd)
                        )


                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Адрес", fontSize = 14.sp, fontWeight = FontWeight.W600, color = text)
                    Spacer(modifier = Modifier.height(12.dp))

                    //адрес
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "адрес пользователя",
                            color = hint,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp, modifier = Modifier.align(Alignment.CenterStart)
                        )

                        Icon(
                            painter = painterResource(R.drawable.strelka),
                            contentDescription = "",
                            tint = hint,
                            modifier = Modifier
                                .clickable { }
                                .align(Alignment.CenterEnd)
                        )
                    }


                    Spacer(modifier = Modifier.height(16.dp))
                    //карта
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()

                            .height(101.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(background)

                    ) {


                        MATULEme2Theme {
                            Scaffold(modifier = Modifier.fillMaxSize()) {

                                MapScreen(location)
                            }
                        }

                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0x66000000))
                            .clickable {
                                controller.navigate(NavigationRoutes.MAP)
                            }) {

                        }


                        Text(
                            "Посмотреть на карте",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = block,
                            modifier = Modifier
                                .padding(top = 19.dp)
                                .align(Alignment.TopCenter)
                                .clickable {
                                    controller.navigate(NavigationRoutes.MAP)
                                }
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 46.dp)
                                .clip(CircleShape)
                                .size(36.dp)
                                .background(accent)
                                .clickable {

                                }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.point),
                                contentDescription = "", tint = block,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(20.dp)
                                    .clickable {
                                        controller.navigate(NavigationRoutes.MAP)
                                    }

                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Спосооб оплаты",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = text
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    //Всё что связано с банк. картой
                    Box(modifier = Modifier.fillMaxWidth()) {
                        //Картинка в контейнере (банк карты)
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(background)
                                .align(Alignment.CenterStart),
                            contentAlignment = Alignment.Center
                            /*.clip(RoundedCornerShape(12.dp))*/
                        )
                        {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(R.drawable.creditcard),
                                contentDescription = "",
                                alignment = Alignment.Center

                            )

                        }


                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 52.dp)
                        )
                        {
                            Text(
                                "Dbl Card", color = text,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "карта пользователя",
                                color = hint,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }

                        Icon(
                            painter = painterResource(R.drawable.strelka),
                            contentDescription = "",
                            tint = hint,
                            modifier = Modifier
                                .clickable { }
                                .align(Alignment.CenterEnd)
                        )

                    }

                }
            }

            Spacer(modifier = Modifier.height(25.dp))
            CheckDelivery("Сумма", text, hint)
            Spacer(modifier = Modifier.height(7.dp))
            CheckDelivery("Доставка", text, hint)

            Spacer(modifier = Modifier.height(23.dp))
            Canvas(Modifier.fillMaxWidth()) {
                val height = size.height
                val width = size.width
                drawLine(
                    color = hint,
                    start = Offset(0f, size.height / 2),
                    end = Offset(size.width, size.height / 2),
                    strokeWidth = 4f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
                )
            }
            Spacer(modifier = Modifier.height(22.dp))

            CheckDelivery("Итого", accent, text)

            Spacer(modifier = Modifier.height(25.dp))

            ButtonExit(buttontext = "Подтвердить") {
                vm.updatestate(state.copy(dialog = false))
                back.value = 8.dp
            }


        }
        if (!state.dialog) DialogCheckOut(controller)
    }

}

@Composable
fun DialogCheckOut(controller: NavHostController) {
    Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(block)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(15.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            Spacer(modifier = Modifier.height(40.dp))
            Box(modifier = Modifier
                .clip(CircleShape)
                .size(134.dp)
                .background(goluboi)
                .clickable {

                })
            {
                Image(
                    contentDescription = "",
                    painter = painterResource(R.drawable.happy),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(66.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Вы успешно оформили заказ",
                color = text,
                fontFamily = textfam,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 100.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            ButtonExit(
                buttontext = "Вернуться К Покупкам",
                modifier = Modifier.padding(horizontal = 80.dp)
            )
            {
                controller.navigate(NavigationRoutes.MAIN)
            }

            Spacer(modifier = Modifier.height(40.dp))


        }

    }
}