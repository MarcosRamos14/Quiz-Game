package com.example.quizgame.framework.di

import com.example.quizgame.framework.remoteDataSource.QuestionDataSource
import com.example.quizgame.framework.remoteDataSource.QuestionRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindQuestionDataSource(dataSource: QuestionRemoteDataSourceImpl) : QuestionDataSource
}