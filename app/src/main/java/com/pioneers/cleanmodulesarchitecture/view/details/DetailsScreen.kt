package com.pioneers.cleanmodulesarchitecture.view.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pioneers.domain.model.Coin
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailsScreen(navigator: DestinationsNavigator?, item: Coin?=null) {
    androidx.compose.material.Surface() {
//        Scaffold(modifier = Modifier.fillMaxSize(),
//            backgroundColor = Color.Red) {

            item?.let { it1 ->
                Text(
                    text = it1.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    //   modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
   //     }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {

    DetailsScreen(null)

}

