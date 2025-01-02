package com.cale.mccammon.kmp.trivia.presentation.mvi

import com.cale.mccammon.kmp.trivia.domain.model.AnswerUI
import com.cale.mccammon.kmp.trivia.domain.model.QuestionUI

/**
 * Enumeration of view actions
 */
sealed interface QuestionAction {
    /**
     * Fetch a question
     */
    data object FetchQuestion : QuestionAction

    /**
     * User answers the question
     * @param questionUI question the user is answering
     * @param answer the user's answer
     */
    data class AnswerQuestion(val questionUI: QuestionUI, val answer: AnswerUI?) : QuestionAction
}