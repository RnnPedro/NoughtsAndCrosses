package com.example.noughtsandcrosses

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noughtsandcrosses.ui.GameBoard
import com.example.noughtsandcrosses.ui.Player

@ExperimentalFoundationApi
@Composable
fun GameScreen(viewModel: GameViewModel){
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif,
            color = Color(0xA36089C7),
            text ="Noughts & Crosses")
//      STATUS LABEL =
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = state.gameStatusLabel)
            if (!state.tieGame && (state.playerWinner == Player.NONE)) {
                Text(text = "Turn")
            } else if (state.playerWinner != Player.NONE) {
                Text(text = "WON!")
            }
        }

        Box(
            modifier = Modifier
                .width(300.dp)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            GameBoard(viewModel)
        }

        Button(onClick = { viewModel.onAction(UserActions.PlayAgainButtonClicked) }) {
            Text(text = "Play Again!")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun Preview(){
    val viewModel = GameViewModel()
    GameScreen(viewModel)
}