package com.cale.mccammon.kmp.trivia.data.repository.model

import kotlinx.serialization.SerialName

enum class QuestionType {
    @SerialName("multiple")
    MULTIPLE,
    @SerialName("boolean")
    BOOLEAN
}