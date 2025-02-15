package com.example.matuleme2.presentation.screens.myCart

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.ResistanceConfig
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.matuleme2.R
import com.example.matuleme2.presentation.screens.checkout.CheckDelivery
import com.example.matuleme2.presentation.screens.components.ButtonExit
import com.example.matuleme2.presentation.screens.components.SpacerHeight
import com.example.matuleme2.presentation.screens.components.SpacerWight
import com.example.matuleme2.presentation.screens.components.TopBar
import com.example.matuleme2.presentation.ui.theme.accent
import com.example.matuleme2.presentation.ui.theme.background
import com.example.matuleme2.presentation.ui.theme.block
import com.example.matuleme2.presentation.ui.theme.hint
import com.example.matuleme2.presentation.ui.theme.red
import com.example.matuleme2.presentation.ui.theme.text
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun myCart(controller: NavHostController) {

    val vm = viewModel { MyCartViewModel() }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp, vertical = 60.dp
                )
        ) {

            TopBar(controller, "Корзина", FontWeight.W600) { }
            SpacerHeight(16)

            val items = remember { mutableStateListOf<ItemModel>() }

            // Добавление тестовых данных
            LaunchedEffect(Unit) {
                if (items.isEmpty()) {
                    items.addAll(listOf(
                        ItemModel("1", "Item 1", 5),
                        ItemModel("2", "Item 2", 3),
                        ItemModel("3", "Item 3", 8),
                        ItemModel("4", "Item 2", 3),
                        ItemModel("5", "Item 2", 3),
                        ItemModel("6", "Item 2", 3),
                        ItemModel("7", "Item 2", 3),


                    ))
                }
            }
            LazyColumn(contentPadding = PaddingValues(bottom = 250.dp)) {
                items(
                    items = items,
                    key = { it.id }
                ) { item ->
                    SwipeableItem(
                        item = item,
                        onDelete = { items.remove(item) },
                        onCountChange = { newCount ->
                            if (newCount == 0) {
                                items.remove(item)
                            } else {
                                val index = items.indexOfFirst { it.id == item.id }
                                if (index != -1) {
                                    items[index] = item.copy(count = newCount)
                                }
                            }
                        }
                    )
                    SpacerHeight(14)
                }

            }


/*
            LazyColumn {
                item {

                    Text(text = "{$} товара", fontWeight = FontWeight.Medium, color = text)
                    SpacerHeight(8)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .background(block)
                    )
                    {

                        Row
                    }
                }
            }*/


        }

        //Белый контейнер снизу
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(block)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(horizontal = 20.dp, vertical = 34.dp)
            ) {

                CheckDelivery("Сумма", text, hint)
                SpacerHeight(10)
                CheckDelivery("Доставка", text, hint)

                SpacerHeight(16)
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

                SpacerHeight(18)
                CheckDelivery("Итого", accent, text)

                SpacerHeight(30)

                ButtonExit(buttontext = "Оформить заказ") { }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalWearMaterialApi::class)
@Composable
fun SwipeableItem(
    item: ItemModel,
    onDelete: () -> Unit,
    onCountChange: (Int) -> Unit
) {
    val swipeState = rememberSwipeableState(initialValue = SwipeDirection.NONE)
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    // Размеры кнопок
    val buttonWidth = 56.dp
    val maxSwipeOffset = with(density) { buttonWidth.toPx() }

    val anchors = mapOf(
        -maxSwipeOffset to SwipeDirection.LEFT,
        0f to SwipeDirection.NONE,
        maxSwipeOffset to SwipeDirection.RIGHT
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(104.dp)
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal,
                resistance = ResistanceConfig(20f)
            )

    ) {

        // Левый контейнер (синий с кнопками)
        LeftSwipeContent(
            count = item.count,
            onIncrement = { onCountChange(item.count + 1) },
            onDecrement = { onCountChange(item.count - 1) },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(buttonWidth)
        )

        // Правый контейнер (красный с удалением)
        RightSwipeContent(
            onDelete = onDelete,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(buttonWidth)
        )

        // Основной контейнер
        Box(
            modifier = Modifier
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(block)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )

            }
        }

        // Автоматическое закрытие при отпускании
        LaunchedEffect(swipeState.targetValue) {
            if (swipeState.targetValue != SwipeDirection.NONE) {
                delay(2000)
                swipeState.animateTo(SwipeDirection.NONE)
            }
        }
    }
}

@Composable
private fun LeftSwipeContent(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(accent)
            .padding(vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onIncrement,
            modifier = Modifier.size(14.dp)
        ) {
            Icon(painter = painterResource(R.drawable.plus), tint = block, contentDescription = "Увеличить")
        }
        SpacerHeight(10)
        Text("$count", color = Color.White, style = MaterialTheme.typography.titleMedium)
        SpacerHeight(10)
        IconButton(
            onClick = { if (count > 0) onDecrement() },
            modifier = Modifier.size(14.dp)
        ) {
            Icon(painter = painterResource(R.drawable.minus), tint = block, contentDescription =  "Уменьшить")
        }
    }

}

@Composable
private fun RightSwipeContent(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(red)
            .clickable(onClick = onDelete),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.bagbag),
            contentDescription = "Delete",
            tint = block,
            modifier = Modifier.size(20.dp)
        )
    }
}

enum class SwipeDirection {
    NONE, LEFT, RIGHT
}

data class ItemModel(
    val id: String,
    val text: String,
    val count: Int
)

