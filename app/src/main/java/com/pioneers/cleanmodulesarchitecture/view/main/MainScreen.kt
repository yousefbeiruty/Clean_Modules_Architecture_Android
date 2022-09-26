package com.pioneers.cleanmodulesarchitecture.view.main


import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pioneers.cleanmodulesarchitecture.R
import com.pioneers.cleanmodulesarchitecture.utils.AssetParamType
import com.pioneers.cleanmodulesarchitecture.view.NavGraphs
import com.pioneers.cleanmodulesarchitecture.view.appCurrentDestinationAsState
import com.pioneers.cleanmodulesarchitecture.view.destinations.*


import com.pioneers.cleanmodulesarchitecture.view.details.DetailsScreen
import com.pioneers.cleanmodulesarchitecture.view.main.viewmodel.MainViewModel
import com.pioneers.cleanmodulesarchitecture.view.startAppDestination
import com.pioneers.domain.model.Coin
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

private const val TAG = "MainScreen"
sealed class BottomNavItem(
    val direction: DirectionDestinationSpec,
    var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem(HomeScreenDestination,"Home", R.drawable.ic_home, "home")
    object MyNetwork : BottomNavItem(NetworkScreenDestination,"My Network", R.drawable.ic_networt, "my_network")
    object AddPost : BottomNavItem(AddPostScreenDestination,"Post", R.drawable.ic_post, "add_post")
    object Notification : BottomNavItem(NotificationScreenDestination,"Notification", R.drawable.ic_notification, "notification")
    object Jobs : BottomNavItem(JobScreenDestination,"Jobs", R.drawable.ic_job, "jobs")
}

sealed class RoutOfScreens(var title: String, var screen_route: String) {
    object Details : RoutOfScreens("Details", "details")
}
@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreenView(viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
       DestinationsNavHost(navController = navController,navGraph = NavGraphs.root)

//        BottomNavigation(navController = navController)
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
        val currentDestination: TypedDestination<*> = navController.appCurrentDestinationAsState().value
            ?: NavGraphs.root.startAppDestination
        BottomNavigation {
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
                selected = currentDestination == item.direction,
                onClick = {
//                    navController.navigate(item.screen_route) {
//                        navController.graph.startDestinationRoute?.let { screen_route ->
//                            popUpTo(screen_route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                    navController.navigateTo(item.direction) {
                        launchSingleTop = true
                    }
                }
            )
        }
        }
    }
}



//@Destination
@Composable
fun NavigationGraph(navController: NavController) {
//    NavHost(
//        navController as NavHostController,
//
//        startDestination = BottomNavItem.Home.screen_route) {
//        composable(BottomNavItem.Home.screen_route) {
//            HomeScreen(EmptyDestinationsNavigator)
//        }
//        composable(BottomNavItem.MyNetwork.screen_route) {
//            NetworkScreen(EmptyDestinationsNavigator)
//        }
//        composable(BottomNavItem.AddPost.screen_route) {
//            AddPostScreen(EmptyDestinationsNavigator)
//        }
//        composable(BottomNavItem.Notification.screen_route) {
//            NotificationScreen(EmptyDestinationsNavigator)
//        }
//        composable(BottomNavItem.Jobs.screen_route) {
//            JobScreen(EmptyDestinationsNavigator)
//        }
//        composable(
//            "${RoutOfScreens.Details.screen_route}/{coin}",
//            arguments = listOf(navArgument("coin") {
//                type = AssetParamType()
//            })
//        ) {
//           // DetailsScreen(item = viewModel.selectedItem)
//            DetailsScreen(EmptyDestinationsNavigator)
//        }

 //   }
}

@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val viewModel: MainViewModel = hiltViewModel()
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
                contentAlignment = Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
    if (data.isNotEmpty() && error.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
                .wrapContentSize(Alignment.Center)
        ) {
            showDialog = false
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
                    CoinItem(item, viewModel::onSelectItem, navigator)
                }
            }

        }
    } else {
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
private fun mToast(context: Context){
    Toast.makeText(context, "This is a Sample Toast", Toast.LENGTH_LONG).show()
}

@Composable
fun CoinItem(coin: Coin, navCallBack: ((Coin) -> Unit)?, navigator: DestinationsNavigator) {
    var isExpanded by remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                Log.d(TAG, "CoinItem: ")
                navigator.navigate(DetailsScreenDestination(coin))
                mToast(mContext)
                navCallBack?.invoke(coin)

            }
    ) {
        Row(modifier = Modifier
            .animateContentSize()
            .clickable {

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
                        Log.d(TAG, "CoinItem: ")
                        navigator.navigate(DetailsScreenDestination(coin))
                        mToast(mContext)
                        navCallBack?.invoke(coin)
                    }
            )
        }
    }
}

@Destination
@Composable
fun NetworkScreen(navigator: DestinationsNavigator) {
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

@Destination
@Composable
fun AddPostScreen(navigator: DestinationsNavigator) {
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

@Destination
@Composable
fun NotificationScreen(navigator: DestinationsNavigator) {
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

@Destination
@Composable
fun JobScreen(navigator: DestinationsNavigator) {
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