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

    @Query("SELECT * FROM ${DbConstants.QUIZ_TABLE_NAME} ORDER BY ${DbConstants.QUIZ_COLUMN_INFO_SCORE}")
    fun loadGame() : Flow<List<GameEntity>>
}