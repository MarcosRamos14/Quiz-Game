package com.example.quizgame.framework.di

import com.example.quizgame.framework.remoteDataSource.QuestionAnswerDataSource
import com.example.quizgame.framework.remoteDataSource.QuestionAnswerRemoteDataSourceImpl
import com.example.quizgame.framework.localDataSource.SaveGameDataSource
import com.example.quizgame.framework.localDataSource.SaveGameDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindQuestionDataSource(dataSource: QuestionAnswerRemoteDataSourceImpl) : QuestionAnswerDataSource

    @Binds
    fun bindSaveGameDataSource(dataSource: SaveGameDataSourceImpl) : SaveGameDataSource
}