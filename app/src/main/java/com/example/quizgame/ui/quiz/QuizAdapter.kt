package com.example.quizgame.ui.quiz

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.core.domain.model.AnswerResultDTO
import com.example.core.domain.model.QuestionDTO
import com.example.quizgame.R
import com.example.quizgame.ui.quiz.QuizAdapterItem.Companion.diff

class QuizAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<QuizAdapterItem, BaseQuizViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseQuizViewHolder {
        return when (viewType) {
            QuizAdapterItem.COUNTER_TYPE -> QuizCounterViewHolder.create(parent)
            QuizAdapterItem.QUESTION_TYPE -> QuizQuestionViewHolder.create(parent)
            QuizAdapterItem.ANSWER_TYPE -> QuizAnswerViewHolder.create(parent, onItemClick)
            else -> throw Exception("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseQuizViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    fun submitQuestion(question: QuestionDTO, answerResult: AnswerResultDTO? = null ) {
        val items = mutableListOf<QuizAdapterItem>()
        items.add(QuizAdapterItem.QuizCounter(itemCount))
        items.add(QuizAdapterItem.QuizQuestion(question))
        question.answerOptions?.forEach {
            items.add(QuizAdapterItem.QuizAnswer(
                it,
                correctColor = if (answerResult != null && answerResult.answer == it) {
                    if (answerResult.result == true) R.color.quiz_option_green else R.color.quiz_option_red
                } else null))
        }
        submitList(items)
    }
}