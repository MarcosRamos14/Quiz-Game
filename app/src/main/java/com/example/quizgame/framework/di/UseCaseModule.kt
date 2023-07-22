package com.example.quizgame.framework.di

import com.example.core.usecase.GetQuestionUseCase
import com.example.core.usecase.GetQuestionUseCaseImpl
import com.example.core.usecase.PostAnswerUseCase
import com.example.core.usecase.PostAnswerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetQuestionUseCase(useCase: GetQuestionUseCaseImpl) : GetQuestionUseCase

    @Binds
    fun bindPostAnswerUseCase(useCase: PostAnswerUseCaseImpl) : PostAnswerUseCase
}