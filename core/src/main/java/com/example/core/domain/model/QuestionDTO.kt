package com.example.core.domain.model

data class QuestionDTO(
    val id: Int?,
    val question: String?,
    val answerOptions: List<String>?
)
