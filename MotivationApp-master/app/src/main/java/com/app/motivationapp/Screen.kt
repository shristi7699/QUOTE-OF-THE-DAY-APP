package com.app.motivationapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.motivationapp.data.remote.Motivation
import com.app.motivationapp.ui.theme.IconButtonColor
import com.app.motivationapp.ui.theme.SystemBackgroundColor
import com.app.motivationapp.ui.theme.nunito_regular
import com.app.motivationapp.ui.theme.nunito_semibold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    state: Motivation?,
    onCLick: () -> Unit
) {


    val brush1 = Brush.horizontalGradient(
        listOf(Color.Green, Color.Yellow)
    )
    val brush2 = Brush.linearGradient(
        listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.error
        )
    )
    val brush3 = Brush.horizontalGradient(
        listOf(Color.White, Color.White)
    )
    var colorIndex by remember { mutableStateOf(0) }
    val colorList = listOf(brush2,brush3,brush1)

    val isEnabled = if (state != null) true else false
    var cardColor1 by remember { mutableStateOf(brush2) }
    var cardColor2 by remember { mutableStateOf(brush3) }
    var cardColor3 by remember { mutableStateOf(brush1) }

    var currentSelectedColor by remember { mutableStateOf(cardColor1) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Motivation App",
                        fontSize = 25.sp,
                        fontFamily = nunito_semibold,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(
                        modifier = Modifier.background(
                            color = IconButtonColor, shape = RoundedCornerShape(10.dp)
                        ),
                        onClick = {
                            //Todo
                        },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = IconButtonColor)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SystemBackgroundColor)
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                onClick = {
                    onCLick()
                    colorIndex = (colorIndex + 1) % colorList.size
                    currentSelectedColor = colorList[colorIndex]

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isEnabled) Color.White else Color.White.copy(
                        alpha = 0.5f
                    )
                )
            ) {
                Text(
                    text = "Refresh",
                    fontSize = 20.sp,
                    fontFamily = nunito_semibold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        },
        containerColor = SystemBackgroundColor,
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            brush = colorList[(colorIndex + 1) % colorList.size]
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 5.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            brush = colorList[(colorIndex + 2) % colorList.size]
                        )
                )
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(
                            horizontal = 22.dp,
                            vertical = 10.dp
                        ),
                    elevation = CardDefaults.cardElevation(6.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = colorList[colorIndex]
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (state != null) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState()),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp),
                                    text = state.quote,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontFamily = nunito_regular,
                                    color = if (cardColor1 == currentSelectedColor) Color.White else if (cardColor2 == currentSelectedColor) Color.Black else Color.White
                                )
                                Text(
                                    text = state.author,
                                    fontSize = 15.sp,
                                    fontFamily = nunito_semibold,
                                    color = if (cardColor1 == currentSelectedColor) Color.White else if (cardColor2 == currentSelectedColor) Color.Black else Color.White,
                                    textAlign = TextAlign.End
                                )

                            }
                        } else {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(30.dp),
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }

}

