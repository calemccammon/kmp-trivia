package com.cale.mccammon.kmp.trivia.data.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val type: QuestionType,
    val difficulty: QuestionDifficulty,
    val category: String,
    val question: String,
    @SerialName("correct_answer")
    val correctAnswer: String,
    @SerialName("incorrect_answers")
    val incorrectAnswers: List<String>
)
