package com.pioneers.cleanmodulesarchitecture.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.pioneers.cleanmodulesarchitecture.view.main.MainApp
import com.pioneers.cleanmodulesarchitecture.view.ui.theme.CleanModulesArchitectureTheme
import com.pioneers.cleanmodulesarchitecture.view.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainScreenActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
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
                    CompositionLocalProvider() {
                        MainApp()
                    }
                //    MainScreenView(viewModel)
                }
            }
        }
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

    }
}