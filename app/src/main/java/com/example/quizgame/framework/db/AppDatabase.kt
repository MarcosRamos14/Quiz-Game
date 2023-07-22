package com.example.quizgame.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quizgame.framework.db.dao.GameDao
import com.example.quizgame.framework.db.entity.GameEntity

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao() : GameDao
}