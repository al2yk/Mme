package com.example.matuleme2.presentation.screens.checkout.map

import android.annotation.SuppressLint
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.matuleme2.presentation.screens.components.MapScreen
import com.example.matuleme2.presentation.screens.components.iconback
import com.example.matuleme2.presentation.ui.theme.MATULEme2Theme

@RequiresApi(Build.VERSION_CODES.S)
@Preview
@Composable
fun Preview() {

    MapFullScreen(rememberNavController(), null)

}

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapFullScreen(controller: NavHostController, CurLoc: Location?) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(modifier = Modifier.align(Alignment.TopStart).padding(top = 50.dp)){
            iconback(controller)

        }
        Spacer(modifier = Modifier.height(20.dp))

        MATULEme2Theme {
            Scaffold(modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))) {
                MapScreen(CurLoc)
            }
        }
    }
}