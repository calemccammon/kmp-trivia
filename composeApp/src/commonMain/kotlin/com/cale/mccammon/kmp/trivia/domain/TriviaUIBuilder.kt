package com.cale.mccammon.kmp.trivia.domain

import com.cale.mccammon.kmp.trivia.domain.model.QuestionUI
import com.cale.mccammon.kmp.trivia.data.repository.model.Question
import com.cale.mccammon.kmp.trivia.domain.model.AnswerUI
import com.cale.mccammon.kmp.trivia.domain.model.ErrorUI

/**
 * Builder for the UI models
 */
interface TriviaUIBuilder {
    /**
     * Builds the question UI from the API model
     * @param input the API model
     */
    fun buildQuestion(
        input: Question
    ): QuestionUI

    /**
     * Validates the answer for the given question
     * @param questionUI the question with the answer to be validated
     * @param answer the answer to validate
     */
    fun validateAnswer(
        questionUI: QuestionUI,
        answer: AnswerUI?
    ): QuestionUI

    /**
     * Builds the error UI from the given exception
     * @param ex the exception to build the error UI from
     */
    fun buildError(ex: Exception): ErrorUI
}