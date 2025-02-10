import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.matuleme2.R
import com.example.matuleme2.presentation.navigation.bottombar.BottomBarRoutes
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.subtextdark

@Composable
fun BottomBar(controller: NavHostController) {
    //Лист хранящий все направления в БотомБар
    val screens = listOf(BottomBarRoutes.MainScreen, BottomBarRoutes.FavScreen, BottomBarRoutes.BuckScreen, BottomBarRoutes.NotScreen, BottomBarRoutes.ProfileScreen)
    Box() {
        //Переменная контроля стека
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        //информацию о текущем экране или фрагменте, на котором находится пользователь.
        val currentRoute = navBackStackEntry?.destination?.route

        //Рисовка БотомБара
        Image(imageVector = ImageVector.vectorResource(R.drawable.bottombar),
            modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.FillBounds,
            contentDescription = "")

        //Строка из иконок в БотомБар
        Row(modifier = Modifier.padding(bottom = 12.dp, top = 8.dp), verticalAlignment = Alignment.Bottom) {
            screens.forEach { screen ->
                //Условие которое поднимает Иконку с Корзиной выше других
                if(screen.route != BottomBarRoutes.BuckScreen.route) {
                    Column(modifier = Modifier.weight(1f)
                        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                            //
                            if(currentRoute != screen.route) {
                                controller.navigate(screen.route)
                            }
                        },horizontalAlignment = Alignment.CenterHorizontally) {
                        var selectedColor = subtextdark
                        if(currentRoute == screen.route) {
                            selectedColor = accent
                        }
                        Spacer(modifier = Modifier.height(38.dp))
                        Icon(imageVector = ImageVector.vectorResource(id = screen.resourceId!!),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "", tint = selectedColor)
                    }
                }
                //То есть если screen.route = корзина поднимаем её вверх
                else {
                    Icon(imageVector = ImageVector.vectorResource(id = screens[2].resourceId!!), tint = Color.White,
                        modifier = Modifier
                            .size(56.dp)
                            //Поднятие по y
                            .offset(y = -20.dp).shadow(elevation = 4.dp, shape = CircleShape)
                            .background(accent, CircleShape).padding(16.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null) {
                                controller.navigate(screens[2].route) {
                                    popUpTo(currentRoute!!) {
                                        inclusive = false
                                    }
                                }
                            },
                        contentDescription = "")
                }

            }
        }
    }
}