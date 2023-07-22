package com.example.quizgame.framework.network.response

import androidx.annotation.Keep
import com.example.core.domain.model.QuestionDTO

@Keep
data class QuestionResponseDTO(
    val id: String?,
    val statement: String?,
    val options: List<String>
)

fun QuestionResponseDTO.toModelQuestion() : QuestionDTO {
    return QuestionDTO(
        id = this.id?.toInt(),
        question = this.statement,
        answerOptions = this.options
    )
}