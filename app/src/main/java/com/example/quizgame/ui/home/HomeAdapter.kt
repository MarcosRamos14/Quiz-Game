package com.example.quizgame.ui.home

import android.view.ViewGroup
import com.example.quizgame.ui.home.HomeAdapterItem.Companion.GAME_TYPE
import com.example.quizgame.ui.home.HomeAdapterItem.Companion.HEADER_TYPE
import com.example.quizgame.ui.home.HomeAdapterItem.Companion.diff
import java.lang.Exception

class HomeAdapter(
) : androidx.recyclerview.widget.ListAdapter<HomeAdapterItem, BaseHomeViewHolder>(diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeViewHolder {
        return when (viewType) {
            HEADER_TYPE -> HomeGameHeaderViewHolder.create(parent)
            GAME_TYPE -> HomeGameItemViewHolder.create(parent)
            else -> throw Exception("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseHomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}