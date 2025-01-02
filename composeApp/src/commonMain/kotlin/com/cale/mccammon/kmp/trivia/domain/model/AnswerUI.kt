package com.cale.mccammon.kmp.trivia.domain.model

sealed interface AnswerUI {
    val text: String
    val isEnabled: Boolean

    data class Correct(
        override val text: String
    ) : AnswerUI {
        override val isEnabled: Boolean = false
    }

    data class Incorrect(
        override val text: String
    ) : AnswerUI {
        override val isEnabled: Boolean = false
    }

    data class Neutral(
        override val text: String,
        override val isEnabled: Boolean
    ) : AnswerUI
}
