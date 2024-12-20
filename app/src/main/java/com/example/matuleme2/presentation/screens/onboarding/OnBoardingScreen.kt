package com.example.matuleme2.presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.matuleme2.R
import com.example.matuleme2.domain.repository.UserRepository
import com.example.matuleme2.presentation.navigation.NavigationRoutes
import com.example.matuleme2.presentation.screens.components.gradient
import com.example.matuleme2.presentation.screens.onboarding.model.OnBoardingUIState
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.disable
import com.example.matuleme2.presentation.ui.theme.subtextlight
import com.example.matuleme2.presentation.ui.theme.text
import com.example.matuleme2.presentation.ui.theme.textfam

@Composable
fun OnBoardingScreen(controller: NavHostController) {
    val viewModel = viewModel { OnBoardingViewModel() }
    val uiState by viewModel.externalOnBoardState.collectAsState()

    var colorbar1 = block
    var colorbar2 = disable
    var colorbar3 = disable
    var sizebar1 = 0.12f
    var sizebar2 = 0.1f
    var sizebar3 = 0.1f
    var padding = 200.dp
    when (uiState.step) {
        2 -> {
            colorbar1 = disable
            colorbar2 = block
            colorbar3 = disable
            sizebar1 = 0.1f
            sizebar2 = 0.12f
            sizebar3 = 0.1f
            padding = 150.dp
        }

        3 -> {
            colorbar1 = disable
            colorbar2 = disable
            colorbar3 = block
            sizebar1 = 0.1f
            sizebar2 = 0.1f
            sizebar3 = 0.12f
            padding = 150.dp
        }
    }
    //Задний фон (какой-то контейнер uistate.background)
    Box(
        modifier = Modifier
            .background(gradient.gradient)
            .fillMaxSize()
            .padding(vertical = 60.dp)
    )
    {
        //Контент (меняется от состояний)

        when (uiState) {
            is OnBoardingUIState.OnBoarding1 -> {
                Column {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 70.dp, end = 70.dp)
                            .fillMaxWidth()
                    ) {
                        //Добавление иконки
                        Icon(
                            painter = painterResource(R.drawable.welcome1),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 15.dp)
                        )

                        Text(
                            uiState.titleResId,
                            fontSize = 30.sp,
                            fontFamily = textfam,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = block,
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Icon(
                        painter = painterResource(R.drawable.welcome2),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    //контейнер с фото
                    Box(modifier = Modifier.align(Alignment.CenterHorizontally))
                    {
                        //Добавление изображения
                        Column(modifier = Modifier.align(Alignment.TopCenter)) {
                            Image(
                                modifier = Modifier
                                    .size(400.dp)
                                    .align(Alignment.CenterHorizontally),
                                painter = painterResource(R.drawable.b),
                                contentDescription = ""
                            )

                        }
                        Image(
                            modifier = Modifier
                                .height(520.dp)
                                .width(315.dp)
                                .align(Alignment.TopCenter),
                            painter = painterResource(R.drawable.groupwel1),
                            contentDescription = ""
                        )
                    }

                }
            }


            is OnBoardingUIState.OnBoarding2 -> {
                //Одна функция
                Page1And2(
                    uiState.iconId, uiState.titleResId,
                    uiState.subtitleResId.toString(), uiState.icon2Id, 350, 150, 0, uiState
                )
            }

            is OnBoardingUIState.OnBoarding3 -> {
                //Одна функция

                Page1And2(
                    uiState.iconId, uiState.titleResId,
                    uiState.subtitleResId.toString(), uiState.icon2Id, 160, 0, 100, uiState
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = padding)
                .align(Alignment.BottomCenter), contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth(sizebar1)
                        .background(colorbar1, RoundedCornerShape(5.dp))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth(sizebar2)
                        .background(colorbar2, RoundedCornerShape(5.dp))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth(sizebar3)
                        .background(colorbar3, RoundedCornerShape(5.dp))
                )
            }
        }

        //Кнопка(всегда стоит на одном месте uistate.btn..)
        Button(
            onClick = {
                if (uiState.step != 3) viewModel.clickNextOnBoarding()
                else {
                    UserRepository.act = 1
                    controller.navigate(NavigationRoutes.SIGNUP) {
                        popUpTo(NavigationRoutes.ONBOARDING) {
                            inclusive = true
                        }
                    }
                }
            },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = text,
                containerColor = background
            ), modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                uiState.btnTitleResId.toString(),
                fontFamily = textfam,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = text
            )
        }
    }
}

//painter = painterResource(R.drawable.group2)
//painter = painterResource(R.drawable.wel2)
@Composable
fun Page1And2(
    image: Int,
    text: String,
    subtitle: String,
    icon: Int,
    size: Int,
    paddingB: Int,
    paddingT: Int,
    uiState: OnBoardingUIState,

    ) {

    Column() {
        Box(modifier = Modifier.fillMaxWidth())
        {
            Image(
                painter = painterResource(image),
                contentDescription = "",
                modifier = Modifier
                    .size(400.dp)
                    .padding(start = 0.dp, bottom = 50.dp)
            )
            Image(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier
                    .size(size.dp)
                    .padding(bottom = paddingB.dp, top = paddingT.dp)
            )

        }

        Text(
            text,
            fontFamily = textfam,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            color = block,
            lineHeight = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            subtitle,
            fontFamily = textfam,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = subtextlight,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

    }
}
