package com.pioneers.cleanmodulesarchitecture.view

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pioneers.cleanmodulesarchitecture.R
import com.pioneers.cleanmodulesarchitecture.view.main.MainApp
import com.pioneers.cleanmodulesarchitecture.view.ui.theme.CleanModulesArchitectureTheme
import com.pioneers.cleanmodulesarchitecture.view.main.viewmodel.MainViewModel
import com.pioneers.cleanmodulesarchitecture.view.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val SplashWaitTime: Long = 3000

@AndroidEntryPoint
class MainScreenActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
    var flag=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   // val viewModel: MainViewModel by viewModels()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            CleanModulesArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
//                    DestinationsNavHost(navController = navController,navGraph = NavGraphs.root){
////                        composable(HomeScreenDestination){
////                            HomeScreen(navigator = destinationsNavigator)
////                        }
//                    }
              //      CompositionLocalProvider() {
                        var splashScreenVisible by androidx.compose.runtime.remember {
                           mutableStateOf(true)  }
                        var mainScreenVisible by remember { mutableStateOf(false) }
//                        LaunchedEffect(key1 = true) {
                            CoroutineScope(Dispatchers.Main).launch {
                                delay(SplashWaitTime)
                                splashScreenVisible = false
                                mainScreenVisible = true
                            }
                //        }
                        if (splashScreenVisible) {
                            SplashScreen()
                        } else if (mainScreenVisible) {
                            MainApp()
                        }
            //        }
                //    MainScreenView(viewModel)
                }
            }
        }
    }
}
@Composable
fun SplashScreen(){
//    Column(modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .crossfade(true)
//                .placeholder(R.drawable.splash_bg).build(),
//            contentScale = ContentScale.Crop,
//            contentDescription = null,
//            modifier = Modifier
//                .size(200.dp)
//                .clip(CircleShape)
//        )
//
//    }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painterResource(R.drawable.splash_bg),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )


        Text(text = "Bitcoin", modifier = Modifier.fillMaxSize()
            .wrapContentHeight()
            ,textAlign =TextAlign.Center,
            style = MaterialTheme.typography.h3,
            color = if (isSystemInDarkTheme()) {
                White
            } else Color.Black
        )



    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanModulesArchitectureTheme {
        SplashScreen()
    }
}