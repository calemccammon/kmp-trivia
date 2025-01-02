package com.cale.mccammon.kmp.trivia.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cale.mccammon.kmp.trivia.data.network.Network
import com.cale.mccammon.kmp.trivia.data.repository.TriviaRepository
import com.cale.mccammon.kmp.trivia.data.repository.TriviaRepositoryImpl
import com.cale.mccammon.kmp.trivia.domain.TriviaUIBuilder
import com.cale.mccammon.kmp.trivia.domain.TriviaUIBuilderImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuestionViewModel : ViewModel() {

    private val builder: TriviaUIBuilder = TriviaUIBuilderImpl()

    private val repository: TriviaRepository = TriviaRepositoryImpl(Network())

    private val _uiState = MutableStateFlow<QuestionState>(QuestionState.Initial)

    val uiState: StateFlow<QuestionState> = _uiState.asStateFlow()

    /**
     * Handles the given view action
     * @param action
     */
    fun handleAction(action: QuestionAction) {
        viewModelScope.launch {
            _uiState.value = try {
                when (action) {
                    is QuestionAction.FetchQuestion -> {
                        QuestionState.ShowingQuestion.AwaitingAnswer(
                            builder.buildQuestion(
                                repository.fetchQuestion()
                            )
                        )
                    }
                    is QuestionAction.AnswerQuestion -> {
                        QuestionState.ShowingQuestion.AnswerRevealed(
                            builder.validateAnswer(
                                action.questionUI,
                                action.answer
                            )
                        )
                    }
                }
            } catch (ex: Exception) {
                QuestionState.Error(builder.buildError(ex))
            }
        }
    }
}