package com.example.quizgame.framework.network.response

import androidx.annotation.Keep

@Keep
data class AnswerRequestDTO(
    val answer: String?
)