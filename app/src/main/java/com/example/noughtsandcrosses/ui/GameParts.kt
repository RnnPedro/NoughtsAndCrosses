package com.example.noughtsandcrosses.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noughtsandcrosses.GameViewModel
import com.example.noughtsandcrosses.UserActions


@OptIn(ExperimentalAnimationApi::class)
@ExperimentalFoundationApi
@Composable
fun GameBoard(viewModel: GameViewModel){
    val state = viewModel.state

    //DRAW THE GAME BOARD
    Canvas(modifier = Modifier
        .fillMaxSize()
    ){
        drawLine(
            color = Color.Gray,
            start = Offset(x = 0f, y = size.height*1/3),
            end = Offset(x = size.width, y = size.height*1/3),
            strokeWidth = 6f
        )
        drawLine(
            color = Color.Gray,
            start = Offset(x = 0f, y = size.height*2/3),
            end = Offset(x = size.width, y = size.height*2/3),
            strokeWidth = 6f
        )
        drawLine(
            color = Color.Gray,
            start = Offset(x = size.width*1/3, y = 0f),
            end = Offset(x = size.width*1/3, y = size.height),
            strokeWidth = 6f
        )
        drawLine(
            color = Color.Gray,
            start = Offset(x = size.width*2/3, y = 0f),
            end = Offset(x = size.width*2/3, y = size.height),
            strokeWidth = 6f
        )
    }

    //CELLS OF THE BOARD
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        cells = GridCells.Fixed(3)){
        viewModel.boardItems.forEach{ (cellNumber, boardCellValue) ->
            item {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ){ viewModel.onAction(UserActions.BoardTapped(cellNumber)) },
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(
                        visible = viewModel.boardItems[cellNumber] != Player.NONE,
                        enter = scaleIn(tween(1000))
                    ) {
                        if (boardCellValue == Player.CIRCLE) {
                            Circle()
                        } else if (boardCellValue == Player.CROSS) {
                            Cross()
                        }
                    }
                }
            }
        }
    }
    AnimatedVisibility(
        visible = viewModel.state.playerWinner != Player.NONE,
        enter = fadeIn(tween(2000))
    ) {
        // DRAW THE WINNER LINE (WINNER SEQUENCE)
        when {
            (state.winSequence == Sequence.HORIZONTAL1) -> WinHorizontalLine1()
            (state.winSequence == Sequence.HORIZONTAL2) -> WinHorizontalLine2()
            (state.winSequence == Sequence.HORIZONTAL3) -> WinHorizontalLine3()
            (state.winSequence == Sequence.VERTICAL1) -> WinVerticalLine1()
            (state.winSequence == Sequence.VERTICAL2) -> WinVerticalLine2()
            (state.winSequence == Sequence.VERTICAL3) -> WinVerticalLine3()
            (state.winSequence == Sequence.DIAGONAL1) -> WinDiagonalLine1()
            (state.winSequence == Sequence.DIAGONAL2) -> WinDiagonalLine2()
        }
    }

}

@Composable
fun Circle(){
    Canvas(modifier = Modifier
        .size(80.dp)
        .padding(5.dp)
        ){
        drawCircle(
            color = Color.Blue,
            style = Stroke(20f)
        )
    }
}


@Composable
fun Cross(){
    Canvas(modifier = Modifier
        .size(80.dp)
        .padding(5.dp)
        ){
        drawLine(
            color = Color.Yellow,
            start = Offset(x= 0f, y= 0f),
            end = Offset(x= size.width, y= size.height),
            strokeWidth = 20f
        )
        drawLine(
            color = Color.Yellow,
            start = Offset(x= size.width, y= 0f),
            end = Offset(x= 0f, y= size.height),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinHorizontalLine1(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = 0f, y = size.width * 1/6) ,
            end = Offset(x = size.width, y = size.height * 1/6),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinHorizontalLine2(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = 0f, y = size.width * 3/6) ,
            end = Offset(x = size.width, y = size.height * 3/6),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinHorizontalLine3(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = 0f, y = size.width * 5/6) ,
            end = Offset(x = size.width, y = size.height * 5/6),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinVerticalLine1(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = size.width * 1/6, y = 0f) ,
            end = Offset(x = size.width * 1/6, y= size.height),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinVerticalLine2(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = size.width * 3/6, y = 0f) ,
            end = Offset(x = size.width * 3/6, y= size.height),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinVerticalLine3(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = size.width * 5/6, y = 0f) ,
            end = Offset(x = size.width * 5/6, y= size.height),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinDiagonalLine1(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = 0f , y = 0f) ,
            end = Offset(x = size.width, y= size.height),
            strokeWidth = 20f
        )
    }
}

@Composable
fun WinDiagonalLine2(){
    Canvas(modifier = Modifier
        .fillMaxSize()){
        drawLine(
            color = Color.Red,
            start = Offset(x = size.width , y = 0f) ,
            end = Offset(x = 0f, y= size.height),
            strokeWidth = 20f
        )
    }
}

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Test(){
    Box(modifier = Modifier
        .size(300.dp)){
        GameBoard(viewModel = viewModel())
        WinHorizontalLine1()
        WinHorizontalLine2()
        WinHorizontalLine3()
        WinVerticalLine1()
        WinVerticalLine2()
        WinVerticalLine3()
        WinDiagonalLine1()
        WinDiagonalLine2()
    }
}


