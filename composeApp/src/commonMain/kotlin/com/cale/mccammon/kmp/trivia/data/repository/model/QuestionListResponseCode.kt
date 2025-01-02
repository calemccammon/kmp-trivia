package com.cale.mccammon.kmp.trivia.data.repository.model

import com.cale.mccammon.kmp.trivia.data.repository.EnumIntSerializer
import kotlinx.serialization.Serializable

private class ResponseCodeSerializer : EnumIntSerializer<QuestionListResponseCode>(
    "response_code",
    { it.code },
    { v -> QuestionListResponseCode.entries.first { it.code == v } }
)

@Serializable(with = ResponseCodeSerializer::class)
enum class QuestionListResponseCode(val code: Int) {
    SUCCESS(0),
    NO_RESULTS(1),
    INVALID_PARAMETER(2),
    TOKEN_NOT_FOUND(3),
    TOKEN_EMPTY(4),
    RATE_LIMIT(5)
}