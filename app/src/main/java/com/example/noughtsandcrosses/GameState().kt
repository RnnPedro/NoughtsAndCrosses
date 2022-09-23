package com.example.noughtsandcrosses.ui

data class GameState(
    var gameStatusLabel: String = "NOUGHT",
    var playerTurn: Player = Player.CIRCLE,
    var tieGame: Boolean = false,
    var playerWinner: Player = Player.NONE,
    var winSequence: Sequence = Sequence.NONE
)

enum class Player{
    CIRCLE,
    CROSS,
    NONE
}

enum class Sequence{
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2,
    NONE
}