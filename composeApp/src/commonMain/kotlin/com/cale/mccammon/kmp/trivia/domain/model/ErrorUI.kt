package com.cale.mccammon.kmp.trivia.domain.model

import org.jetbrains.compose.resources.StringResource

data class ErrorUI(
    val message: StringResource,
    val button: ButtonUI
)
