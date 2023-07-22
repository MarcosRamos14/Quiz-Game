package com.example.quizgame.framework.di

import com.example.core.data.repository.QuestionAnswerRepository
import com.example.quizgame.framework.repositoryImpl.QuestionAnswerAnswerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindQuestionRepository(repository: QuestionAnswerAnswerRepositoryImpl) : QuestionAnswerRepository
}