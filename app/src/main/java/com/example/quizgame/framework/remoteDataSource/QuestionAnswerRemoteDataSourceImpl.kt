package com.example.quizgame.framework.remoteDataSource

import com.example.core.domain.model.AnswerResultDTO
import com.example.quizgame.framework.network.QuizApi
import com.example.quizgame.framework.network.response.AnswerRequestDTO
import com.example.quizgame.framework.network.response.QuestionResponseDTO
import com.example.quizgame.framework.network.response.toModelAnswerResult
import javax.inject.Inject

class QuestionAnswerRemoteDataSourceImpl @Inject constructor(
    private val quizApi: QuizApi
) : QuestionAnswerDataSource {

    override suspend fun getQuestion(): QuestionResponseDTO {
        return quizApi.getQuestion()
    }

    override suspend fun postAnswer(questionId: String, answer: String): AnswerResultDTO {
        return quizApi.submitAnswer(questionId, AnswerRequestDTO(answer)).toModelAnswerResult(answer)
    }
}