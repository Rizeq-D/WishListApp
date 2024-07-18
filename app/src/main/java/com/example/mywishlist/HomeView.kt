package com.example.mywishlist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishlist.data.Wish
import kotlin.math.roundToInt
import androidx.compose.foundation.layout.offset
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.SwipeableState
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppBarView(title = "WishList") {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                containerColor = Color.Black,
                onClick = {
                    Toast.makeText(context, "Adding", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AddScreen.route + "/0")
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        val wishlist = viewModel.getAllWishesVM.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishlist.value) { wish ->
                val dismissState = rememberSwipeableState(DismissValue.Default)
                SwipeToDismissBox(
                    wish = wish,
                    navController = navController,
                    dismissState = dismissState,
                    onDismiss = { viewModel.deleteWishVM(wish) }
                )
            }
        }
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SwipeToDismissBox(
    wish: Wish,
    navController: NavController,
    dismissState: SwipeableState<DismissValue>,
    onDismiss: () -> Unit
) {
    val anchors = mapOf(0f to DismissValue.Default, -300f to DismissValue.DismissedToStart)

    Box(
        Modifier
            .fillMaxWidth()
            .swipeable(
                state = dismissState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.25f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        LaunchedEffect(dismissState.currentValue) {
            if (dismissState.currentValue == DismissValue.DismissedToStart) {
                onDismiss()
                dismissState.snapTo(DismissValue.Default)
            }
        }
        WishItem(
            wish = wish,
            modifier = Modifier.offset { IntOffset(dismissState.offset.value.roundToInt(), 0) }
        ) {
            val id = wish.id
            navController.navigate(Screen.AddScreen.route + "/$id")
        }
    }
}

@Composable
fun WishItem(wish: Wish, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() }
            .shadow(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}

enum class DismissValue {
    Default,
    DismissedToStart
}
