package com.example.quizgame.framework.localDataSource

import com.example.core.domain.model.GameDTO
import kotlinx.coroutines.flow.Flow

interface SaveGameDataSource {

    fun getAll() : Flow<List<GameDTO>>

    suspend fun saveFavorite(game: GameDTO)
}