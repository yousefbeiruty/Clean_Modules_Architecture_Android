package com.pioneers.cleanmodulesarchitecture.view.details


import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.pioneers.cleanmodulesarchitecture.view.main.BottomNavigation
import com.pioneers.cleanmodulesarchitecture.view.main.SampleScaffold
import com.pioneers.cleanmodulesarchitecture.view.ui.theme.White
import com.pioneers.domain.model.Coin
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(navigator: DestinationsNavigator?, item: Coin?=null) {

    Scaffold(topBar = {
        TopBar(navigator,"Details Screen")
    }, content = {it->
        DetailsBody(item)
    }
    )
}

@Composable
private fun TopBar(navigator: DestinationsNavigator?,title: String?="") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        IconButton(onClick = {
            navigator?.popBackStack()
        }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(30.dp),
                tint = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else
                    Color.White
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = title.toString(),
                color = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else
                    Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun DetailsBody(item: Coin?) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        item?.let { it1 ->
            Column {
            //    Image
                Text(
                    text = "Bit Coin= ${it1.name}",
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) {
                        White
                    } else Color.Black,
                    //    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {

    DetailsScreen(null)

}

