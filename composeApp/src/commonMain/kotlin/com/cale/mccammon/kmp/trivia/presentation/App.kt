package com.cale.mccammon.kmp.trivia.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cale.mccammon.kmp.trivia.presentation.mvi.QuestionAction
import com.cale.mccammon.kmp.trivia.presentation.mvi.QuestionViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    viewModel: QuestionViewModel = QuestionViewModel()
) {
    MaterialTheme {
        val state by viewModel.uiState.collectAsState()
        val handleAction = { action: QuestionAction ->
            viewModel.handleAction(action)
        }
        QuestionScreen(state, handleAction)
    }
}