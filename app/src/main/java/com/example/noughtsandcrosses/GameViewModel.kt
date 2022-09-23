package com.example.noughtsandcrosses

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.noughtsandcrosses.ui.GameState
import com.example.noughtsandcrosses.ui.Player
import com.example.noughtsandcrosses.ui.Sequence

class GameViewModel : ViewModel() {
    var state by mutableStateOf(GameState())
    val boardItems: MutableMap<Int, Player> = mutableMapOf(
        1 to Player.NONE,
        2 to Player.NONE,
        3 to Player.NONE,
        4 to Player.NONE,
        5 to Player.NONE,
        6 to Player.NONE,
        7 to Player.NONE,
        8 to Player.NONE,
        9 to Player.NONE
    )

    fun onAction(action: UserActions) {
        when (action) {
            is UserActions.BoardTapped -> {
                boardTapped(action.cellNo)
            }
            is UserActions.PlayAgainButtonClicked -> {
                state = GameState()
                boardItems.forEach { (i, _) ->
                    boardItems[i] = Player.NONE
                }
            }
        }
    }


    private fun boardTapped(cellNumber: Int) {
        if(boardItems[cellNumber] == Player.NONE && state.playerWinner == Player.NONE){
            if (state.playerTurn == Player.CIRCLE){
                boardItems[cellNumber] = Player.CIRCLE
                if (hasWon(Player.CIRCLE)){
                    state = state.copy(playerWinner = Player.CIRCLE)
                } else if (checkBoardFull()){
                    state.tieGame = true
                    state.gameStatusLabel = "TIE GAME"
                    state = state.copy(playerTurn = Player.NONE)
                } else {
                    state.gameStatusLabel = "CROSS"
                    state = state.copy(playerTurn = Player.CROSS)
                }
            } else if (state.playerTurn == Player.CROSS) {
                boardItems[cellNumber] = Player.CROSS
                if (hasWon(Player.CROSS)) {
                    state = state.copy(playerWinner = Player.CROSS)
                } else if (checkBoardFull()){
                    state.tieGame = true
                    state.gameStatusLabel = "TIE GAME"
                    state = state.copy(playerTurn = Player.NONE)
                } else {
                    state.gameStatusLabel = "CIRCLE"
                    state = state.copy(playerTurn = Player.CIRCLE)
                }
            }
        }
    }

    private fun hasWon(player: Player): Boolean {
        return when (true){
            checkSequence(1,2,3, player) -> {
                state.winSequence = Sequence.HORIZONTAL1
                return true
            }
            checkSequence(4,5,6, player) -> {
                state.winSequence = Sequence.HORIZONTAL2
                return true
            }
            checkSequence(7,8,9, player) -> {
                state.winSequence = Sequence.HORIZONTAL3
                return true
            }
            checkSequence(1,4,7, player) -> {
                state.winSequence = Sequence.VERTICAL1
                return true
            }
            checkSequence(2,5,8, player) -> {
                state.winSequence = Sequence.VERTICAL2
                return true
            }
            checkSequence(3,6,9, player) -> {
                state.winSequence = Sequence.VERTICAL3
                return true
            }
            checkSequence(1,5,9, player) -> {
                state.winSequence = Sequence.DIAGONAL1
                return true
            }
            checkSequence(3,5,7, player) -> {
                state.winSequence = Sequence.DIAGONAL2
                return true
            }
            else -> false
        }
    }

    private fun checkSequence(a: Int, b: Int, c: Int, player: Player): Any {
        if(player == boardItems[a] && player == boardItems[b] && player == boardItems[c]){
            return true
        }
        return false
    }

    private fun checkBoardFull(): Boolean {
        return !boardItems.containsValue(Player.NONE)
    }
}


