package com.example.quizgame.framework.repositoryImpl

import com.example.quizgame.framework.remoteDataSource.QuestionDataSource
import com.example.core.data.repository.QuestionRepository
import com.example.core.domain.model.QuestionDTO
import com.example.quizgame.framework.network.response.toModelQuestion
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val remoteDataSource: QuestionDataSource
) : QuestionRepository {

    override suspend fun getQuestion(): QuestionDTO {
        return remoteDataSource.getQuestion().toModelQuestion()
    }
}