package com.example.quizgame.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quizgame.databinding.ItemQuizCounterBinding
import com.example.quizgame.databinding.ItemQuizQuestionBinding

class QuizCounterViewHolder(
    itemBinding: ItemQuizCounterBinding
) : BaseQuizViewHolder(itemBinding.root) {

    private val textCounter = itemBinding.textCounter

    override fun bind(item: QuizAdapterItem) {
        (item as? QuizAdapterItem.QuizCounter)?.let {
            textCounter.text = it.counter.toString()
        }
    }

    companion object {
        fun create(parent: ViewGroup): QuizCounterViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemQuizCounterBinding.inflate(inflate, parent, false)
            return QuizCounterViewHolder(itemBinding)
        }
    }
}