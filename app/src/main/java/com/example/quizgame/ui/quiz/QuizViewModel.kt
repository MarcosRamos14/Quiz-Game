package com.example.quizgame.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.core.domain.model.QuestionDTO
import com.example.core.usecase.GetQuestionUseCase
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.quizgame.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val coroutineDispatchers: CoroutinesDispatchers,
    private val getQuestionUseCase: GetQuestionUseCase
) : ViewModel() {

    private val action = MutableLiveData<Action>()
    val state: LiveData<UiState> = action.switchMap {
        liveData(coroutineDispatchers.io()) {
            when (it) {
                Action.Load -> {
                    getQuestionUseCase().watchStatus(
                        loading = {
                            emit(UiState.Loading)
                        },
                        success = { question ->
                            emit(UiState.Success(question))
                        },
                        error = {
                            emit(UiState.Error)
                        }
                    )
                }
            }
        }
    }

    fun getQuestion() {
        action.value = Action.Load
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val question: QuestionDTO) : UiState()
        object Error : UiState()
    }

    sealed class Action {
        object Load : Action()
    }
}