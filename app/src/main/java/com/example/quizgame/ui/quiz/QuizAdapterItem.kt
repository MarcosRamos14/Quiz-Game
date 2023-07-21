package com.example.quizgame.ui.quiz

import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.AnswerDTO
import com.example.core.domain.model.QuestionDTO

sealed class QuizAdapterItem {

    abstract val diffId: String
    abstract val viewType: Int

    data class QuizQuestion(
        val question: QuestionDTO
        ) : QuizAdapterItem() {
        override val diffId: String get() = question.id.toString()
        override val viewType: Int get() = QUESTION_TYPE
    }

    data class QuizAnswer(val answer: AnswerDTO) : QuizAdapterItem() {
        override val diffId: String get() = answer.id.toString()
        override val viewType: Int get() = ANSWER_TYPE
    }

    companion object {
        const val QUESTION_TYPE = 0
        const val ANSWER_TYPE = 1

        val diff = object : DiffUtil.ItemCallback<QuizAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: QuizAdapterItem,
                newItem: QuizAdapterItem
            ): Boolean {
                return oldItem.diffId == newItem.diffId
            }

            override fun areContentsTheSame(
                oldItem: QuizAdapterItem,
                newItem: QuizAdapterItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}