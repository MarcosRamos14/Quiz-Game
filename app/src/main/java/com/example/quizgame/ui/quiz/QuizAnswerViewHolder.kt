package com.example.quizgame.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.quizgame.R
import com.example.quizgame.databinding.ItemQuizAnswerBinding

class QuizAnswerViewHolder(
    itemBinding: ItemQuizAnswerBinding,
    private val onItemClick: (String) -> Unit
) : BaseQuizViewHolder(itemBinding.root) {

    private val textAnswer = itemBinding.textAnswer

    override fun bind(item: QuizAdapterItem) {
        (item as? QuizAdapterItem.QuizAnswer)?.let {
            textAnswer.text = it.answer

            val textColor = item.correctColor ?: R.color.black
            textAnswer.setTextColor(ContextCompat.getColor(itemView.context, textColor))
            itemView.setOnClickListener {
                onItemClick.invoke(item.answer)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: (String) -> Unit): QuizAnswerViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val itemBinding = ItemQuizAnswerBinding.inflate(inflate, parent, false)
            return QuizAnswerViewHolder(itemBinding, onItemClick)
        }
    }
}