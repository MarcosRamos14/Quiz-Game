package com.example.quizgame.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.GameDTO

sealed class HomeAdapterItem {

    abstract val diffId: String
    abstract val viewType: Int

    data class GameHeader(
        val rankingTitle: String,
        val playerNameTitle: String,
        val scoreTitle: String
    ) : HomeAdapterItem() {
        override val diffId: String get() = rankingTitle + playerNameTitle + scoreTitle
        override val viewType: Int get() = HEADER_TYPE
    }

    data class GameItem(
        val game: GameDTO,
        val position: Int,
        val showDivider: Boolean
        ) : HomeAdapterItem() {
        override val diffId: String get() = game.id.toString()
        override val viewType: Int get() = GAME_TYPE
    }

    companion object {
        const val HEADER_TYPE = 0
        const val GAME_TYPE = 1

        val diff = object : DiffUtil.ItemCallback<HomeAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: HomeAdapterItem,
                newItem: HomeAdapterItem
            ): Boolean {
                return oldItem.diffId == newItem.diffId
            }

            override fun areContentsTheSame(
                oldItem: HomeAdapterItem,
                newItem: HomeAdapterItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}