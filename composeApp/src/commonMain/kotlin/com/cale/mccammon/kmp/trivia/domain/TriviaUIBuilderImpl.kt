package com.cale.mccammon.kmp.trivia.domain

import com.cale.mccammon.kmp.trivia.data.network.TooManyRequestsException
import com.cale.mccammon.kmp.trivia.domain.model.QuestionUI
import com.cale.mccammon.kmp.trivia.data.repository.model.Question
import com.cale.mccammon.kmp.trivia.domain.model.AnswerUI
import com.cale.mccammon.kmp.trivia.domain.model.ButtonUI
import com.cale.mccammon.kmp.trivia.domain.model.ErrorUI
import com.mohamedrejeb.ksoup.entities.KsoupEntities
import kotlinmultiplatformtrivia.composeapp.generated.resources.Res
import kotlinmultiplatformtrivia.composeapp.generated.resources.button_next_question
import kotlinmultiplatformtrivia.composeapp.generated.resources.button_retry
import kotlinmultiplatformtrivia.composeapp.generated.resources.button_show_answer
import kotlinmultiplatformtrivia.composeapp.generated.resources.button_submit
import kotlinmultiplatformtrivia.composeapp.generated.resources.error_generic
import kotlinmultiplatformtrivia.composeapp.generated.resources.error_too_many_requests

/**
 * Default implementation for the UI builder
 */
class TriviaUIBuilderImpl : TriviaUIBuilder {
    override fun buildQuestion(
        input: Question
    ): QuestionUI = QuestionUI(
        category = input.category.fromHtml(),
        question = input.question.fromHtml(),
        answers = input.incorrectAnswers.map {
            it.toNeutralAnswerUI()
        }.plus(input.correctAnswer.toNeutralAnswerUI())
            .shuffled(),
        correctAnswer = input.correctAnswer.fromHtml(),
        buttons = makeQuestionButtons(false)
    )

    override fun validateAnswer(questionUI: QuestionUI, answer: AnswerUI?): QuestionUI {
        return questionUI.copy(
            buttons = makeQuestionButtons(true),
            answers = toggleAnswerListState(questionUI, answer)
        )
    }

    override fun buildError(ex: Exception): ErrorUI {
        return when (ex) {
            is TooManyRequestsException -> {
                ErrorUI(
                    Res.string.error_too_many_requests,
                    retryButton()
                )
            }
            else -> {
                ErrorUI(
                    Res.string.error_generic,
                    retryButton()
                )
            }
        }
    }

    /**
     * Takes the given question UI and toggles the answer list state
     * based on whether the answer is correct
     * @param questionUI the question UI to toggle
     * @param answer the user's answer
     */
    private fun toggleAnswerListState(
        questionUI: QuestionUI,
        answer: AnswerUI?
    ) = questionUI.answers.map {
         when {
             it.text == questionUI.correctAnswer -> {
                 AnswerUI.Correct(it.text)
             }
             answer == it -> {
                 AnswerUI.Incorrect(it.text)
             }
             else -> {
                 it.text.toNeutralAnswerUI(false)
             }
        }
    }

    /**
     * Decodes HTML in the string
     */
    private fun String.fromHtml() = KsoupEntities.decodeHtml(this)

    /**
     * Convert the string to a neutral answer UI
     */
    private fun String.toNeutralAnswerUI(isEnabled: Boolean = true) =
        AnswerUI.Neutral(this.fromHtml(), isEnabled)

    /**
     * Build the list of buttons for the question
     * @param isAnswerValidated flag for disabling or enabling buttons
     */
    private fun makeQuestionButtons(isAnswerValidated: Boolean) = listOf(
        ButtonUI(Res.string.button_submit, !isAnswerValidated),
        ButtonUI(Res.string.button_next_question, true),
        ButtonUI(Res.string.button_show_answer, !isAnswerValidated)
    )

    /**
     * Build the retry button
     */
    private fun retryButton() = ButtonUI(
        Res.string.button_retry,
        true
    )
}