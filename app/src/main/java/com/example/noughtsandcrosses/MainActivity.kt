@file:OptIn(ExperimentalFoundationApi::class)

package com.example.noughtsandcrosses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.noughtsandcrosses.ui.theme.NoughtsAndCrossesTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoughtsAndCrossesTheme {
                val viewModel = viewModel<GameViewModel>()
                GameScreen(viewModel = viewModel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoughtsAndCrossesTheme {
        val viewModel = GameViewModel()
        GameScreen(viewModel)
    }
}