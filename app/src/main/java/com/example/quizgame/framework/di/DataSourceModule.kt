package com.example.quizgame.framework.di

import com.example.quizgame.framework.remoteDataSource.QuestionAnswerDataSource
import com.example.quizgame.framework.remoteDataSource.QuestionAnswerRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindQuestionDataSource(dataSource: QuestionAnswerRemoteDataSourceImpl) : QuestionAnswerDataSource
}