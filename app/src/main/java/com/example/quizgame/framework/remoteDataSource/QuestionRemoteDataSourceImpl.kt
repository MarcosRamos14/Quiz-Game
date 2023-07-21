package com.example.quizgame.framework.remoteDataSource

import com.example.quizgame.framework.network.QuizApi
import com.example.quizgame.framework.network.response.QuestionResponseDTO
import javax.inject.Inject

class QuestionRemoteDataSourceImpl @Inject constructor(
    private val quizApi: QuizApi
) : QuestionDataSource {

    override suspend fun getQuestion(): QuestionResponseDTO {
        return quizApi.getQuestion()
    }
}