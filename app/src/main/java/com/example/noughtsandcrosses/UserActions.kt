package com.example.noughtsandcrosses

sealed class UserActions{
    object PlayAgainButtonClicked: UserActions()
    data class BoardTapped(val cellNo: Int): UserActions()

}
