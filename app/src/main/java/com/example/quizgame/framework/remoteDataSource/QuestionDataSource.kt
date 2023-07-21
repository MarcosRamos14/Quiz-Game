package com.example.quizgame.framework.remoteDataSource

import com.example.quizgame.framework.network.response.QuestionResponseDTO

interface QuestionDataSource {

    suspend fun getQuestion() : QuestionResponseDTO
}