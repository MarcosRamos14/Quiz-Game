package com.example.quizgame.framework.network

import com.example.quizgame.framework.network.response.AnswerRequestDTO
import com.example.quizgame.framework.network.response.AnswerResultResponseDTO
import com.example.quizgame.framework.network.response.QuestionResponseDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuizApi {

    @GET("/question")
    suspend fun getQuestion() : QuestionResponseDTO

    @POST("/answer")
    suspend fun submitAnswer(
        @Query("questionId") questionId: String,
        @Body answerRequestDTO: AnswerRequestDTO
    ): AnswerResultResponseDTO
}