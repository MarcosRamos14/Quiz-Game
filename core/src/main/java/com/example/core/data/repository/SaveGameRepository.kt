package com.example.core.data.repository

import com.example.core.domain.model.GameDTO
import kotlinx.coroutines.flow.Flow

interface SaveGameRepository {

    fun getAll() : Flow<List<GameDTO>>

    suspend fun saveFavorite(game: GameDTO)
}