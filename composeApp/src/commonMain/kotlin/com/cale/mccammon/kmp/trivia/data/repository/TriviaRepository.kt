package com.cale.mccammon.kmp.trivia.data.repository

import com.cale.mccammon.kmp.trivia.data.repository.model.Question

interface TriviaRepository {
    suspend fun fetchQuestion(): Question
}