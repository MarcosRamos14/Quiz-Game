package com.example.quizgame.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: HomeAdapterItem)
}