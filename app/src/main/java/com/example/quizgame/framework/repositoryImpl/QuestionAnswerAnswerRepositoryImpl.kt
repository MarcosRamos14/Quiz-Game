package com.example.quizgame.framework.repositoryImpl

import com.example.core.data.repository.QuestionAnswerRepository
import com.example.core.domain.model.AnswerResultDTO
import com.example.core.domain.model.QuestionDTO
import com.example.quizgame.framework.network.response.toModelQuestion
import com.example.quizgame.framework.remoteDataSource.QuestionAnswerDataSource
import javax.inject.Inject

class QuestionAnswerAnswerRepositoryImpl @Inject constructor(
    private val remoteDataSource: QuestionAnswerDataSource
) : QuestionAnswerRepository {

    override suspend fun getQuestion(): QuestionDTO {
        return remoteDataSource.getQuestion().toModelQuestion()
    }

    override suspend fun postAnswer(questionId: String, answer: String): AnswerResultDTO {
        return remoteDataSource.postAnswer(questionId, answer)
    }
}