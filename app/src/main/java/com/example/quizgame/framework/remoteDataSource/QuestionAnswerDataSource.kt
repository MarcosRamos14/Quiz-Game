package com.example.quizgame.framework.remoteDataSource

import com.example.core.domain.model.AnswerResultDTO
import com.example.quizgame.framework.network.response.QuestionResponseDTO

interface QuestionAnswerDataSource {

    suspend fun getQuestion() : QuestionResponseDTO

    suspend fun postAnswer(questionId: String, answer: String) : AnswerResultDTO
}