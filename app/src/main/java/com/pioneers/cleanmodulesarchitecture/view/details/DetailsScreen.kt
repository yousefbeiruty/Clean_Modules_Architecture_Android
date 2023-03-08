package com.pioneers.cleanmodulesarchitecture.view.details


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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.min
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pioneers.cleanmodulesarchitecture.R


@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DetailsScreen(navigator: DestinationsNavigator?, item: Coin?=null) {

    Scaffold(topBar = {
        TopBar(navigator)
    }, content = {it->
        DetailsBody(item)
    }
    )
}

@Composable
private fun TopBar(navigator: DestinationsNavigator?) {
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
                text = "Details Screen",
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
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(modifier = Modifier.widthIn(min = 300.dp).
                    heightIn(max=200.dp).padding(16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp),
                        ) {
                            ShowImage("")
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(top = 50.dp)
                            ) {
                                Text(
                                    text = it1.name,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSystemInDarkTheme()) {
                                        White
                                    } else Color.Black,
                                    //    modifier = Modifier.align(Alignment.Center),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                    style = MaterialTheme.typography.h5
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun ShowImage(url: String) {
    Column(

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).
            data("https://media.licdn.com/dms/image/C5603AQHRkCNTh0NZaQ/profile-displayphoto-shrink_800_800/0/1562505491030?e=2147483647&v=beta&t=CqF8mF3OWTWOP3zeiYyIyyRxmPMQBNHPoSxztoI7HsE")
                .crossfade(true)
                .placeholder(R.drawable.ic_launcher_background).build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {

    DetailsScreen(null)

}

