package com.example.core.data.repository

import com.example.core.domain.model.QuestionDTO

interface QuestionRepository {

    suspend fun getQuestion() : QuestionDTO
}