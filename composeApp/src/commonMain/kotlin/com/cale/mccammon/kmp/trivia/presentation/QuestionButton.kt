package com.cale.mccammon.kmp.trivia.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cale.mccammon.kmp.trivia.domain.model.ButtonUI
import org.jetbrains.compose.resources.stringResource

@Composable
fun QuestionButton(
    model: ButtonUI,
    modifier: Modifier = Modifier,
    handleAction: () -> Unit
) = Button(
    enabled = model.enabled,
    modifier = modifier.fillMaxWidth(0.92f),
    onClick = handleAction
) {
    Text(stringResource(model.text))
}