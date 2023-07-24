package com.example.quizgame.framework.repositoryImpl

import com.example.core.data.repository.SaveGameRepository
import com.example.core.domain.model.GameDTO
import com.example.quizgame.framework.localDataSource.SaveGameDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveGameRepositoryImpl @Inject constructor(
    private val saveGameDataSource: SaveGameDataSource
) : SaveGameRepository {

    override fun getAll(): Flow<List<GameDTO>> {
        return saveGameDataSource.getAll()
    }

    override suspend fun saveFavorite(game: GameDTO) {
        saveGameDataSource.saveFavorite(game)
    }
}