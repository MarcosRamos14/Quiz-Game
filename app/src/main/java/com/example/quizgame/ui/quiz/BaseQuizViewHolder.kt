package com.example.quizgame.ui.quiz

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseQuizViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: QuizAdapterItem)
}