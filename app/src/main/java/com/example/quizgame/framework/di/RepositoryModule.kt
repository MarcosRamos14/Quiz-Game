package com.example.quizgame.framework.di

import com.example.core.data.repository.QuestionRepository
import com.example.quizgame.framework.repositoryImpl.QuestionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindQuestionRepository(repository: QuestionRepositoryImpl) : QuestionRepository
}