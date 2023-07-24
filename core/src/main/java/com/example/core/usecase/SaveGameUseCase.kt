package com.example.core.usecase

import com.example.core.data.repository.SaveGameRepository
import com.example.core.domain.model.GameDTO
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SaveGameUseCase {

    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>

    data class Params(val id: Int, val playerName: String, val score: Int, val position: Int)

}

class SaveGameUseCaseImpl @Inject constructor(
    private val saveGameRepository: SaveGameRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<SaveGameUseCase.Params, Unit>(), SaveGameUseCase {

    override suspend fun doWork(params: SaveGameUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            saveGameRepository.saveFavorite(
                GameDTO(params.id, params.playerName, params.score, params.position)
            )
            ResultStatus.Success(Unit)
        }
    }
}