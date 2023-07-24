package com.example.quizgame.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.constants.DbConstants
import com.example.core.domain.model.GameDTO

@Entity(tableName = DbConstants.QUIZ_TABLE_NAME)
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = DbConstants.QUIZ_COLUMN_INFO_ID)
    val id: Int,
    @ColumnInfo(name = DbConstants.QUIZ_COLUMN_INFO_PLAYER_NAME)
    val playerName: String,
    @ColumnInfo(name = DbConstants.QUIZ_COLUMN_INFO_SCORE)
    val score: Int,
    @ColumnInfo(name = DbConstants.QUIZ_COLUMN_INFO_POSITION)
    val position: Int
)