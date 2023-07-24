package com.example.quizgame.ui.result

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.core.usecase.SaveGameUseCase
import com.example.quizgame.R
import com.example.quizgame.ui.extensions.watchStatus
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ResultViewModel @Inject constructor(
    private val coroutineContext: CoroutineContext,
    private val saveGameUseCase: SaveGameUseCase
) : ViewModel() {

    private val actionSave = MutableLiveData<Action>()
    val stateSave: LiveData<UiState> = actionSave.switchMap {
        liveData(coroutineContext) {
            when (it) {
                is Action.AddGame -> {
                    it.resultViewArgs.run {
                        saveGameUseCase.invoke(
                            SaveGameUseCase.Params(
                                id = id ?: 0,
                                playerName = playerName ?: "",
                                score = score ?: 0,
                                position = position ?: 0
                            )
                        ).watchStatus(
                            success = {
                                emit(UiState.Success)
                            },
                            error = {
                                emit(UiState.Error(R.string.error_save_game))
                            }
                        )
                    }
                }
            }
        }
    }

    fun saveGame(resultViewArgs: ResultViewArgs) {
        actionSave.value = Action.AddGame(resultViewArgs)
    }

    sealed class UiState {
        object Success : UiState()
        data class Error(@StringRes val messageResId: Int) : UiState()
    }

    sealed class Action {
        data class AddGame(val resultViewArgs: ResultViewArgs) : Action()
    }
}