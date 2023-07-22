package com.example.core.data.repository

import com.example.core.domain.model.AnswerResultDTO
import com.example.core.domain.model.QuestionDTO

interface QuestionAnswerRepository {

    suspend fun getQuestion() : QuestionDTO

    suspend fun postAnswer(questionId: String, answer: String) : AnswerResultDTO
}