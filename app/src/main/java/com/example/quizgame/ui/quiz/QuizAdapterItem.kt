package com.example.quizgame.ui.quiz

import androidx.annotation.ColorRes
import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.QuestionDTO

sealed class QuizAdapterItem {

    abstract val diffId: String
    abstract val viewType: Int

    data class QuizCounter(val counter: Int) : QuizAdapterItem() {
        override val diffId: String get() = counter.toString()
        override val viewType: Int get() = COUNTER_TYPE
    }

    data class QuizQuestion(
        val question: QuestionDTO
        ) : QuizAdapterItem() {
        override val diffId: String get() = question.id.toString()
        override val viewType: Int get() = QUESTION_TYPE
    }

    data class QuizAnswer(val answer: String, @ColorRes val correctColor: Int?) : QuizAdapterItem() {
        override val diffId: String get() = answer + correctColor.toString()
        override val viewType: Int get() = ANSWER_TYPE
    }

    companion object {
        const val COUNTER_TYPE = 0
        const val QUESTION_TYPE = 1
        const val ANSWER_TYPE = 2

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