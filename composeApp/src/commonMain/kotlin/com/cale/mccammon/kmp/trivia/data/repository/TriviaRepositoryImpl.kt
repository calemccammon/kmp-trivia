package com.cale.mccammon.kmp.trivia.data.repository

import com.cale.mccammon.kmp.trivia.data.network.Network
import com.cale.mccammon.kmp.trivia.data.network.TooManyRequestsException
import com.cale.mccammon.kmp.trivia.data.repository.model.Question
import com.cale.mccammon.kmp.trivia.data.repository.model.QuestionList
import com.cale.mccammon.kmp.trivia.data.repository.model.QuestionListResponseCode

class TriviaRepositoryImpl(
    private val network: Network = Network()
) : TriviaRepository {

    private suspend fun getListOfQuestions(): QuestionList {
       return network.get(
               "https://opentdb.com/api.php",
               emptyMap(),
               mapOf(
                   "amount" to "1"
               )
           )
       }

    override suspend fun fetchQuestion(): Question {
        return getListOfQuestions().let {
            if (it.responseCode == QuestionListResponseCode.RATE_LIMIT) {
                throw TooManyRequestsException()
            } else {
                it.results.first()
            }
        }
    }
}