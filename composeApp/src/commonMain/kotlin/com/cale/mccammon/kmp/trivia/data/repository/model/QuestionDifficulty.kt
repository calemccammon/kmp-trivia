package com.cale.mccammon.kmp.trivia.data.repository.model

import kotlinx.serialization.SerialName

enum class QuestionDifficulty {
    @SerialName("easy")
    EASY,
    @SerialName("medium")
    MEDIUM,
    @SerialName("hard")
    HARD
}