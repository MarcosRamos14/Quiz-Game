package com.example.quizgame.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quizgame.databinding.ItemQuizAnswerBinding

class QuizAnswerViewHolder(
    itemBinding: ItemQuizAnswerBinding
) : BaseQuizViewHolder(itemBinding.root) {

    private val textAnswer = itemBinding.textAnswer

    override fun bind(item: QuizAdapterItem) {
        (item as? QuizAdapterItem.QuizAnswer)?.let {
             textAnswer.text = it.answer
        }
    }

    companion object {
        fun create(parent: ViewGroup): QuizAnswerViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemQuizAnswerBinding.inflate(inflate, parent, false)
            return QuizAnswerViewHolder(itemBinding)
        }
    }
}