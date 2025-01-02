package com.cale.mccammon.kmp.trivia.presentation.mvi

import com.cale.mccammon.kmp.trivia.domain.model.ErrorUI
import com.cale.mccammon.kmp.trivia.domain.model.QuestionUI

/**
 * View state enumerations
 */
sealed interface QuestionState {
    /**
     * Initial state
     */
    data object Initial : QuestionState

    /**
     * Enumerations for states where a question is displayed
     */
    sealed interface ShowingQuestion : QuestionState {
        /**
         * UI model
         */
        val questionUI: QuestionUI

        /**
         * Indicates the view is waiting for the user to answer
         */
        data class AwaitingAnswer(
            override val questionUI: QuestionUI
        ) : ShowingQuestion

        /**
         * The answer has been revealed
         */
        data class AnswerRevealed(
            override val questionUI: QuestionUI
        ) : ShowingQuestion
    }

    /**
     * Error state
     */
    data class Error(
        val errorUI: ErrorUI
    ) : QuestionState
}