package com.example.quizgame.framework.di

import com.example.core.usecase.GetQuestionUseCase
import com.example.core.usecase.GetQuestionUseCaseImpl
import com.example.core.usecase.GetSaveGameUseCase
import com.example.core.usecase.GetSaveGameUseCaseImpl
import com.example.core.usecase.PostAnswerUseCase
import com.example.core.usecase.PostAnswerUseCaseImpl
import com.example.core.usecase.SaveGameUseCase
import com.example.core.usecase.SaveGameUseCaseImpl
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

    @Binds
    fun bindSaveGameUseCase(useCase: SaveGameUseCaseImpl) : SaveGameUseCase

    @Binds
    fun bindGetSaveGameUseCase(useCase: GetSaveGameUseCaseImpl) : GetSaveGameUseCase
}