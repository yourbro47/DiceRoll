package org.example.dicerollapp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dicegame.composeapp.generated.resources.Res
import dicegame.composeapp.generated.resources.dice1
import dicegame.composeapp.generated.resources.dice2
import dicegame.composeapp.generated.resources.dice3
import dicegame.composeapp.generated.resources.dice4
import dicegame.composeapp.generated.resources.dice5
import dicegame.composeapp.generated.resources.dice6
import dicegame.composeapp.generated.resources.frontpage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val listOfImages = listOf(
            Res.drawable.dice1,
            Res.drawable.dice2,
            Res.drawable.dice3,
            Res.drawable.dice4,
            Res.drawable.dice5,
            Res.drawable.dice6


        )
        var isFirstPlayer = remember { mutableStateOf(true) }
        var isSecondPlayer = remember { mutableStateOf(false) }
        var diceToVisible = remember { mutableStateOf(-1) }
        var _1stPlayerScore = remember { mutableStateOf(0) }
        var _2ndPlayerScore = remember { mutableStateOf(0) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF000000)),
            contentAlignment = Alignment.Center // This aligns all children inside the Box
        ) {
            if (_1stPlayerScore.value >= 20 || _2ndPlayerScore.value >= 20) {
                // Game Over Section
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (_1stPlayerScore.value >= 20) "Player 1 Wins!" else "Player 2 Wins!",
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Button(onClick = {
                        // Reset game state
                        _1stPlayerScore.value = 0
                        _2ndPlayerScore.value = 0
                        diceToVisible.value = -1
                        isFirstPlayer.value = true
                    }, shape = RoundedCornerShape(50.dp)) {
                        Text(
                            text = "Restart",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Welcome to Dice Roll Game",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                    if (diceToVisible.value != -1) {
                        Image(
                            painter = painterResource(listOfImages[diceToVisible.value]),
                            contentDescription = null,
                            modifier = Modifier.size(500.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.frontpage),
                            contentDescription = null,
                            modifier = Modifier
                                .size(500.dp)
                        )
                    }
                    Button(
                        onClick = {
                            diceToVisible.value = (0..5).random()
                            if (isFirstPlayer.value) {
                                _1stPlayerScore.value += (diceToVisible.value + 1)
                                isFirstPlayer.value = !isFirstPlayer.value
                            } else {
                                _2ndPlayerScore.value += (diceToVisible.value + 1)
                                isFirstPlayer.value = !isFirstPlayer.value
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 25.dp) // Optional: to add some padding
                            .size(200.dp, 50.dp), // Optional: to set size
                        shape = RoundedCornerShape(50.dp)
                    ) {
                        Text(
                            text = "Roll Dice for ${if (isFirstPlayer.value) "Player 1" else "Player 2"}",
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 34.dp, end = 34.dp, top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                        ) {
                            Text(
                                text = "Player 1 Score: ${_1stPlayerScore.value}",
                                color = Color.White
                            )
                        }
                        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                            Text(
                                text = "Player 2 Score: ${_2ndPlayerScore.value}",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}