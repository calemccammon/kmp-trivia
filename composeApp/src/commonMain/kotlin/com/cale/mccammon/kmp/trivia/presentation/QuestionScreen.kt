package com.cale.mccammon.kmp.trivia.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cale.mccammon.kmp.trivia.presentation.mvi.QuestionAction
import com.cale.mccammon.kmp.trivia.presentation.mvi.QuestionState
import org.jetbrains.compose.resources.stringResource

@Composable
fun QuestionScreen(
    state: QuestionState,
    handleAction: (QuestionAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (state) {
            is QuestionState.ShowingQuestion -> {
                ShowingQuestionState(state, handleAction)
            }
            is QuestionState.Error -> {
                ErrorState(state, handleAction)
            }
            is QuestionState.Initial -> {
                handleAction(QuestionAction.FetchQuestion)
            }
        }
    }
}

@Composable
private fun ShowingQuestionState(
    state: QuestionState.ShowingQuestion,
    handleAction: (QuestionAction) -> Unit
) {
    with(state.questionUI) {
        Text(text = category, fontWeight = FontWeight.Bold)
        Text(question)

        val (selectedOption, onOptionSelected) = remember {
            mutableStateOf(answers.first())
        }

        QuestionAnswerGroup(
            selectedOption,
            onOptionSelected,
            answers
        )

        buttons.forEachIndexed { index, buttonUI ->
            QuestionButton(
                model = buttonUI,
                handleAction = {
                    //TODO this could be better
                    when (index) {
                        0 -> {
                            handleAction(
                                QuestionAction.AnswerQuestion(state.questionUI, selectedOption)
                            )
                        }
                        1 -> {
                            handleAction(QuestionAction.FetchQuestion)
                        }
                        2 -> {
                            handleAction(
                                QuestionAction.AnswerQuestion(state.questionUI, null)
                            )
                        }
                        else -> {

                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun ErrorState(
    state: QuestionState.Error,
    handleAction: (QuestionAction) -> Unit
) {
    Text(
        text = stringResource(state.errorUI.message),
        modifier = Modifier.fillMaxSize(0.92f),
        fontWeight = FontWeight.Bold
    )
    QuestionButton(model = state.errorUI.button) {
        handleAction(QuestionAction.FetchQuestion)
    }
}