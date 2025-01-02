package com.cale.mccammon.kmp.trivia.data.repository.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class QuestionList(
    @SerialName("response_code")
    val responseCode: QuestionListResponseCode,
    @JsonNames("result", "results")
    val results: List<Question>
)
