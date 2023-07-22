package com.example.quizgame.framework.network.response

import androidx.annotation.Keep
import com.example.core.domain.model.AnswerResultDTO

@Keep
data class AnswerResultResponseDTO(
    val result: Boolean?
)

fun AnswerResultResponseDTO.toModelAnswerResult(answerId: String) : AnswerResultDTO {
    return AnswerResultDTO(
        result = this.result ?: true,
        answer = answerId
    )
}