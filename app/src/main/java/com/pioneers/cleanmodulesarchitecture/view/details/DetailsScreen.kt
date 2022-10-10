package com.pioneers.cleanmodulesarchitecture.view.details


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Surface(modifier = Modifier.fillMaxSize(),
        ) {
            item?.let { it1 ->
                Text(
                    text = "Bit Coin= ${it1.name}",
                    fontWeight = FontWeight.Bold,
                    color = if(isSystemInDarkTheme()) {
                            White
                        }else Color.Black,
                 //    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {

    DetailsScreen(null)

}

