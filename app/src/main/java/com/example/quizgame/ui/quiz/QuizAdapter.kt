package com.example.quizgame.ui.quiz

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.quizgame.ui.quiz.QuizAdapterItem.Companion.diff

class QuizAdapter : ListAdapter<QuizAdapterItem, BaseQuizViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseQuizViewHolder {
        return when (viewType) {
            QuizAdapterItem.QUESTION_TYPE -> QuizQuestionViewHolder.create(parent)
            QuizAdapterItem.ANSWER_TYPE -> QuizAnswerViewHolder.create(parent)
            else -> throw Exception("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseQuizViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}