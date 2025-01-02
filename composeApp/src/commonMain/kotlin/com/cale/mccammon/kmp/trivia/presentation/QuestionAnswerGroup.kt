package com.cale.mccammon.kmp.trivia.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.cale.mccammon.kmp.trivia.domain.model.AnswerUI

@Composable
fun QuestionAnswerGroup(
    selectedOption: AnswerUI,
    onOptionSelected: (AnswerUI) -> Unit,
    answers: List<AnswerUI>,
    modifier: Modifier = Modifier
) {
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(
        modifier = modifier.selectableGroup().fillMaxWidth(0.92f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        answers.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        color = when (text) {
                            is AnswerUI.Correct -> {
                                Color.Green
                            }
                            is AnswerUI.Incorrect -> {
                                Color.Red
                            }
                            is AnswerUI.Neutral -> {
                                Color.Transparent
                            }
                        }
                    )
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            if (text.isEnabled) {
                                onOptionSelected(text)
                            }
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = text.text,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}