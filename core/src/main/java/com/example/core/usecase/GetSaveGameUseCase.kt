package com.example.core.usecase

import com.example.core.data.repository.SaveGameRepository
import com.example.core.domain.model.GameDTO
import com.example.core.usecase.base.CoroutinesDispatchers
import com.example.core.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetSaveGameUseCase {
    suspend operator fun invoke(params: Unit = Unit) : Flow<List<GameDTO>>
}

class GetSaveGameUseCaseImpl @Inject constructor(
    private val saveGameRepository: SaveGameRepository,
    private val dispatchers: CoroutinesDispatchers
) : FlowUseCase<Unit, List<GameDTO>>(), GetSaveGameUseCase {

    override suspend fun createFlowObservable(params: Unit): Flow<List<GameDTO>> {
        return withContext(dispatchers.io()) {
            saveGameRepository.getAll()
        }
    }
}