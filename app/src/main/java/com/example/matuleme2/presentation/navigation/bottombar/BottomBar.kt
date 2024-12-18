import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
    val screens = listOf(BottomBarRoutes.MainScreen, BottomBarRoutes.FavScreen, BottomBarRoutes.BuckScreen, BottomBarRoutes.NotScreen, BottomBarRoutes.ProfileScreen)
    Box() {
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Image(imageVector = ImageVector.vectorResource(R.drawable.bottombar),
            modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.FillBounds,
            contentDescription = "")
        Row(modifier = Modifier.padding(bottom = 12.dp, top = 8.dp), verticalAlignment = Alignment.Bottom) {
            screens.forEach { screen ->
                Column(modifier = Modifier.weight(1f)
                    .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                        if(currentRoute != screen.route) {
                            controller.navigate(screen.route) {
                                currentRoute?.let {
                                    popUpTo(it) {
                                        inclusive = true
                                    }
                                }
                            }
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
        }
    }
}