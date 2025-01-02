package com.cale.mccammon.kmp.trivia.domain.model

data class QuestionUI(
    val category: String,
    val question: String,
    val correctAnswer: String,
    val answers: List<AnswerUI>,
    val buttons: List<ButtonUI>
)
