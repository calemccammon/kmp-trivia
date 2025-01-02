package com.cale.mccammon.kmp.trivia.presentation.mvi

import com.cale.mccammon.kmp.trivia.domain.model.ErrorUI
import com.cale.mccammon.kmp.trivia.domain.model.QuestionUI

sealed interface QuestionState {
    data object Initial : QuestionState

    sealed interface ShowingQuestion : QuestionState {
        val questionUI: QuestionUI

        data class AwaitingAnswer(
            override val questionUI: QuestionUI
        ) : ShowingQuestion

        data class AnswerRevealed(
            override val questionUI: QuestionUI
        ) : ShowingQuestion
    }

    data class Error(
        val errorUI: ErrorUI
    ) : QuestionState
}