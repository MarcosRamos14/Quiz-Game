package com.example.quizgame.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quizgame.databinding.ItemQuizQuestionBinding

class QuizQuestionViewHolder(
    itemBinding: ItemQuizQuestionBinding
) : BaseQuizViewHolder(itemBinding.root) {

    private val textQuestion = itemBinding.textQuestion

    override fun bind(item: QuizAdapterItem) {
        (item as? QuizAdapterItem.QuizQuestion)?.let {
            textQuestion.text = it.question.question
        }
    }

    companion object {
        fun create(parent: ViewGroup): QuizQuestionViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemQuizQuestionBinding.inflate(inflate, parent, false)
            return QuizQuestionViewHolder(itemBinding)
        }
    }
}