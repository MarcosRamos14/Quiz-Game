package com.example.quizgame.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.quizgame.databinding.ItemGameRankingBinding

class HomeGameItemViewHolder(
    itemBinding: ItemGameRankingBinding
) : BaseHomeViewHolder(itemBinding.root) {

    private val position = itemBinding.textItemPosition
    private val playerName = itemBinding.textItemName
    private val score = itemBinding.textItemPts

    override fun bind(item: HomeAdapterItem) {
        (item as? HomeAdapterItem.GameItem)?.let {
            it.showDivider
            position.text = it.position.toString()
            playerName.text = it.game.playerName
            score.text = it.game.score.toString()
        }
    }

    companion object {
        fun create(parent: ViewGroup) : HomeGameItemViewHolder {
            val itemBinding = ItemGameRankingBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return HomeGameItemViewHolder(itemBinding)
        }
    }
}