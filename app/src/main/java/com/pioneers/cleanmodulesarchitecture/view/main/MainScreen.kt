package com.pioneers.cleanmodulesarchitecture.view.main


import android.os.Bundle
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pioneers.cleanmodulesarchitecture.R
import com.pioneers.cleanmodulesarchitecture.utils.AssetParamType
import com.pioneers.cleanmodulesarchitecture.view.details.DetailsScreen
import com.pioneers.cleanmodulesarchitecture.view.main.viewmodel.MainViewModel
import com.pioneers.domain.model.Coin


sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object MyNetwork : BottomNavItem("My Network", R.drawable.ic_networt, "my_network")
    object AddPost : BottomNavItem("Post", R.drawable.ic_post, "add_post")
    object Notification : BottomNavItem("Notification", R.drawable.ic_notification, "notification")
    object Jobs : BottomNavItem("Jobs", R.drawable.ic_job, "jobs")
}

sealed class RoutOfScreens(var title: String,var screen_route: String){
    object Details : RoutOfScreens("Details", "details")
}

@Composable
fun MainScreenView(viewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {

        NavigationGraph(navController = navController,viewModel)
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork,
        BottomNavItem.AddPost,
        BottomNavItem.Notification,
        BottomNavItem.Jobs,
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_700),
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(viewModel,navController)
        }
        composable(BottomNavItem.MyNetwork.screen_route) {
            NetworkScreen()
        }
        composable(BottomNavItem.AddPost.screen_route) {
            AddPostScreen()
        }
        composable(BottomNavItem.Notification.screen_route) {
            NotificationScreen()
        }
        composable(BottomNavItem.Jobs.screen_route) {
            JobScreen()
        }
        composable(
            "${RoutOfScreens.Details.screen_route}/{coin}",
            arguments = listOf(navArgument("coin") {
                type = AssetParamType()
            })
        ){
            DetailsScreen(item =viewModel.selectedItem)
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController) {
   // val viewModel = androidx.lifecycle.viewmodel.compose.viewModel<MainViewModel>()
    val state = viewModel.listState.collectAsState()
    val loading = state.value.isLoading
    val error = state.value.error
    val data = state.value.coinList
    var showDialog by remember { mutableStateOf(loading) }



    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment= Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
    if(data.isNotEmpty() && error.isEmpty()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .wrapContentSize(Alignment.Center)
        ) {
            showDialog=false
            LazyColumn {
                items(data) { item ->
//                    Text(
//                        text = item.name,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Black,
//                        modifier = Modifier.align(Alignment.CenterHorizontally),
//                        textAlign = TextAlign.Center,
//                        fontSize = 20.sp
//                    )
                    CoinItem(item,viewModel::onSelectItem,navController)
                }
            }

        }
    }else{
        showDialog = false
        Text(
            text = error,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
          //  modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

}

@Composable
fun CoinItem(coin: Coin, navCallBack: ((Coin) -> Unit)?, navController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navCallBack?.invoke(coin)
            }
    ) {
        Row(modifier = Modifier.animateContentSize().clickable {

        }) {
//            Image(
//               // painter = rememberCoilPainter(request = meal.imageUrl),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(88.dp)
//                    .padding(4.dp)
//                    .align(Alignment.CenterVertically)
//            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.h6
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = coin.rank.toString(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) 10 else 4,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded) Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable {
                        isExpanded = !isExpanded
                    }
            )
        }
    }
}

@Composable
fun NetworkScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "My Network Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun AddPostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Add Post Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Notification Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun JobScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Jobs Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}