package com.example.quizgame.framework.localDataSource

import com.example.core.domain.model.GameDTO
import com.example.quizgame.framework.db.dao.GameDao
import com.example.quizgame.framework.db.entity.GameEntity
import com.example.quizgame.framework.db.entity.toGameModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SaveGameDataSourceImpl @Inject constructor(
    private val gameDao: GameDao
) : SaveGameDataSource {

    override fun getAll(): Flow<List<GameDTO>> {
        return gameDao.loadGame().map {
            it.toGameModel()
        }
    }

    override suspend fun saveFavorite(game: GameDTO) {
        gameDao.insertGame(game.toGameEntity())
    }

    private fun GameDTO.toGameEntity() =
        GameEntity(
            id = id ?: 0,
            playerName = playerName ?: "",
            score = score ?: 0,
            position = position ?: 0
        )
}