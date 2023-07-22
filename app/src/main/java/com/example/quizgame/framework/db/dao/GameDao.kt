package com.example.quizgame.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.constants.DbConstants
import com.example.quizgame.framework.db.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(gameEntity: GameEntity)

    @Query("SELECT * FROM ${DbConstants.QUIZ_TABLE_NAME}")
    fun loadGame() : Flow<List<GameEntity>>

    @Query("SELECT * FROM ${DbConstants.QUIZ_TABLE_NAME} WHERE id = :gameId")
    suspend fun hasGame(gameId: Int) : GameEntity
}