package com.example.quizgame.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.AnswerResultDTO
import com.example.core.domain.model.QuestionDTO
import com.example.core.usecase.GetQuestionUseCase
import com.example.core.usecase.PostAnswerUseCase
import com.example.core.usecase.PostAnswerUseCase.PostAnswerUseCaseParams
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.quizgame.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val coroutineDispatchers: CoroutinesDispatchers,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val postAnswerUseCase: PostAnswerUseCase
) : ViewModel() {

    private var question: QuestionDTO? = null
    private var requestCounter: Int = 0
    private var correctAnswersCount: Int = 0
    private var incorrectAnswersCount: Int = 0
    private var totalQuestionsCount = 0

    private val _isAnswerSelected = MutableLiveData(false)
    val isAnswerSelected: LiveData<Boolean>
        get() = _isAnswerSelected

    private val _isGameFinished = MutableLiveData(false)
    val isGameFinished: LiveData<Boolean>
        get() = _isGameFinished

    private val actionQuestion = MutableLiveData<ActionQuestion>()
    val stateQuestion: LiveData<UiStateQuestion> = actionQuestion.switchMap {
        liveData(coroutineDispatchers.io()) {
            when (it) {
                ActionQuestion.Load -> {
                    if (requestCounter < MAX_REQUEST) {
                        getQuestionUseCase().watchStatus(
                            loading = {
                                emit(UiStateQuestion.Loading)
                            },
                            success = { question ->
                                this@QuizViewModel.question = question
                                emit(UiStateQuestion.Success(question))
                                requestCounter++
                            },
                            error = {
                                emit(UiStateQuestion.Error)
                            }
                        )
                    }
                }
            }
        }
    }

    private val actionAnswer = MutableLiveData<ActionAnswer>()
    val stateAnswer: LiveData<UiStateAnswer> = actionAnswer.switchMap {
        liveData(coroutineDispatchers.io()) {
            when (it) {
                is ActionAnswer.Submit -> {
                    postAnswerUseCase(
                        PostAnswerUseCaseParams(it.questionId, it.answer)
                    ).watchStatus(
                        loading = {
                            emit(UiStateAnswer.Loading)
                        },
                        success = { answerResult ->
                            question?.let { question ->
                                emit(UiStateAnswer.Success(answerResult, question))
                                processAnswerResult(answerResult)
                            }
                        },
                        error = {
                            emit(UiStateAnswer.Error)
                        }
                    )
                }
            }
        }
    }

    fun getQuestion() {
        actionQuestion.value = ActionQuestion.Load
    }

    fun submitAnswer(answer: String) {
        _isAnswerSelected.value?.let { isAnswerSelected ->
            if (!isAnswerSelected) {
                actionAnswer.value = ActionAnswer.Submit(question?.id.toString(), answer)
                _isAnswerSelected.value = true
            }
        }
    }

    fun clearAnswerState() {
        _isAnswerSelected.value = false
    }

    private fun processAnswerResult(answerResult: AnswerResultDTO) {
        viewModelScope.launch {
            answerResult.result?.let { result ->
                totalQuestionsCount++
                if (result) correctAnswersCount++ else incorrectAnswersCount++
                if (totalQuestionsCount == MAX_REQUEST) {
                    _isGameFinished.postValue(true)
                }
            }
        }
    }

    fun getCorrectAnswersCount(): Int {
        return correctAnswersCount
    }

    fun getIncorrectAnswersCount(): Int {
        return incorrectAnswersCount
    }

    fun resetGame() {
        totalQuestionsCount = 0
        requestCounter = 0
        getQuestion()
    }

    sealed class UiStateQuestion {
        object Loading : UiStateQuestion()
        data class Success(val question: QuestionDTO) : UiStateQuestion()
        object Error : UiStateQuestion()
    }

    sealed class ActionQuestion {
        object Load : ActionQuestion()
    }

    sealed class UiStateAnswer {
        object Loading : UiStateAnswer()
        data class Success(
            val answerResult: AnswerResultDTO,
            val question: QuestionDTO
        ) : UiStateAnswer()
        object Error : UiStateAnswer()
    }

    sealed class ActionAnswer {
        data class Submit(val questionId: String, val answer: String) : ActionAnswer()
    }

    companion object {
        private const val MAX_REQUEST = 10
    }
}