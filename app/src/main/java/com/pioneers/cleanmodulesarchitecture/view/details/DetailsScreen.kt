package com.pioneers.cleanmodulesarchitecture.view.details

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.pioneers.domain.model.Coin

@Composable
fun DetailsScreen(item: Coin) {
    val navController = rememberNavController()
    Scaffold {
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
         //   modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}